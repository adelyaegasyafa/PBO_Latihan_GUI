import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract class MakhlukHidup {
    protected String nama;

    public MakhlukHidup(String nama) {
        this.nama = nama;
    }

    public String bernafas() {         
        return nama + " bernafas...";
    }

    public abstract String berjalan();      
}

class Manusia extends MakhlukHidup {
    public Manusia(String nama) {
        super(nama);
    }

    @Override
    public String berjalan() {
        return nama + " berjalan dengan 2 kaki";
    }
}

// ===== INTERFACE =====
interface Terbang {
    String terbang();
}

interface Berenang {
    String berenang();

    default String selam() {     
        return "Menyelam dengan cara standar...";
    }
}

class Hewan extends MakhlukHidup implements Terbang, Berenang {
    public Hewan(String nama) {
        super(nama);
    }

    @Override
    public String berjalan() {
        return nama + " berjalan dengan 4 kaki";
    }

    @Override
    public String terbang() {
        return nama + " terbang";
    }

    @Override
    public String berenang() {
        return nama + " berenang";
    }
}

public class GuiAbstractInterface extends JPanel implements ActionListener {

    private JTextField txtNama = new JTextField("Rio");
    private JComboBox<String> cmbJenis = new JComboBox<String>(new String[] { "Manusia", "Hewan" });
    private JCheckBox chkHapus = new JCheckBox("Hapus log setiap aksi");
    private JButton btnJalan = new JButton("Berjalan");
    private JButton btnTerbang = new JButton("Terbang");
    private JButton btnBerenang = new JButton("Berenang");
    private JTextArea txtLog = new JTextArea(8, 30);

    public GuiAbstractInterface() {
        setLayout(new BorderLayout(5, 5));                     // Layout 1: BorderLayout
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel form = new JPanel(new GridLayout(3, 2, 6, 6));
        form.add(new JLabel("Nama:"));
        form.add(txtNama);
        form.add(new JLabel("Jenis:"));
        form.add(cmbJenis);
        form.add(new JLabel(""));
        form.add(chkHapus);

        txtLog.setEditable(false);
        txtLog.setToolTipText("Klik dua kali untuk menghapus log");
        JScrollPane scroll = new JScrollPane(txtLog);

        JPanel tombol = new JPanel(new FlowLayout());
        tombol.add(btnJalan);
        tombol.add(btnTerbang);
        tombol.add(btnBerenang);

        add(form, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(tombol, BorderLayout.SOUTH);

        btnJalan.addActionListener(this);
        btnTerbang.addActionListener(this);
        btnBerenang.addActionListener(this);

        txtLog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {             
                    txtLog.setText("");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sumber = e.getSource();                      
        MakhlukHidup m = buatMakhluk();
        if (m == null) {
            return;
        }

        if (sumber == btnJalan) {
            tulis(m.berjalan());

        } else if (sumber == btnTerbang) {
            if (m instanceof Terbang) {
                Terbang t = (Terbang) m;
                tulis(t.terbang());
            } else {
                info(cmbJenis.getSelectedItem() + " tidak punya kemampuan terbang "
                        + "(tidak meng-implement interface Terbang).");
            }

        } else if (sumber == btnBerenang) {
            if (m instanceof Berenang) {
                Berenang b = (Berenang) m;
                tulis(b.berenang());
                tulis(b.selam());
            } else {
                info(cmbJenis.getSelectedItem() + " tidak punya kemampuan berenang "
                        + "(tidak meng-implement interface Berenang).");
            }
        }
    }

    private MakhlukHidup buatMakhluk() {
        String nama = txtNama.getText().trim();
        if (nama.length() == 0) {
            info("Nama tidak boleh kosong.");
            return null;
        }
        if ("Hewan".equals(cmbJenis.getSelectedItem())) {
            return new Hewan(nama);
        }
        return new Manusia(nama);
    }

    private void tulis(String teks) {
        if (chkHapus.isSelected()) {
            txtLog.setText("");
        }
        txtLog.append(teks + "\n");
    }

    private void info(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Abstract Class & Interface (GUI)");   
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GuiAbstractInterface());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}