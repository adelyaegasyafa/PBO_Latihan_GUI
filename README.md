# Latihan GUI Java (Swing): Abstract Class & Interface

Tugas: **3B - Latihan/Eksplorasi Materi Graphical User Interface pada Java**

| | |
|---|---|
| **NIM** | `F1D02510033` |
| **Nama** | `Adelya Ega Syafa` |

## Deskripsi

Aplikasi GUI sederhana berbasis **Java Swing** yang memperagakan konsep *abstract class* dan *interface*.
Pengguna mengisi nama, memilih jenis makhluk hidup (**Manusia** atau **Hewan**), lalu menekan tombol aksi.
Hasilnya ditampilkan pada kotak log.

- **Abstract class** `MakhlukHidup` punya abstract method `berjalan()`; `Manusia` dan `Hewan` mengisinya dengan cara berbeda (polymorphism).
- **Interface** `Terbang` dan `Berenang` adalah kemampuan tambahan. Hanya `Hewan` yang meng-implement keduanya, sedangkan `Manusia` tidak. Karena itu klik **Terbang** atau **Berenang** pada Manusia memunculkan dialog informasi.
- `Hewan` dibuat sebagai hewan umum yang meng-implement kedua interface tersebut sebagai contoh *multiple inheritance* lewat interface (`extends` satu class, `implements` banyak interface).
- `Berenang` memiliki **default method** `selam()` (Java 8+).

## Cara menjalankan

Syarat: **JDK 8 atau lebih baru**.

```
javac GuiAbstractInterface.java
java GuiAbstractInterface
```

## Struktur file

```
.
├── GuiAbstractInterface.java   # kode program (abstract class, interface, dan GUI)
├── README.md
└── screenshots/                # tangkapan layar aplikasi
```

## 5 Komponen Utama GUI yang Digunakan

| No | Komponen | Class / Interface yang dipakai | Fungsi di program |
|---|---|---|---|
| 1 | **Top-level Container** | `JFrame`, `JOptionPane` | `JFrame` adalah jendela utama aplikasi (dibuat di `main`). `JOptionPane` menampilkan dialog informasi (nama kosong, atau aksi tidak didukung). |
| 2 | **Intermediate Container** | `JPanel`, `JScrollPane` | `JPanel` menjadi wadah komponen: panel utama (`GuiAbstractInterface` sendiri), panel form, dan panel tombol. `JScrollPane` membungkus area log agar bisa di-scroll. |
| 3 | **Atomic Component** | `JLabel`, `JTextField`, `JComboBox`, `JCheckBox`, `JButton`, `JTextArea` | `JLabel` untuk teks keterangan; `JTextField` untuk input nama; `JComboBox` untuk memilih jenis; `JCheckBox` untuk opsi hapus log otomatis; `JButton` untuk tombol aksi; `JTextArea` untuk menampilkan log. |
| 4 | **Layout Classes** | `BorderLayout`, `GridLayout`, `FlowLayout` | `BorderLayout` mengatur panel utama (form di NORTH, log di CENTER, tombol di SOUTH). `GridLayout` (3 baris x 2 kolom) mengatur label dan input pada form. `FlowLayout` menyusun tiga tombol berjajar. |
| 5 | **Event Listener** | `ActionListener` (interface), `MouseAdapter` (adapter class) | `ActionListener`: class `GuiAbstractInterface` meng-*override* `actionPerformed()` untuk menangani klik tiga tombol. `MouseAdapter`: meng-*override* `mouseClicked()` pada area log; klik dua kali akan menghapus isi log. |

### Detail Event Listener

```java
// 1. Event Listener Interface: ActionListener
public class GuiAbstractInterface extends JPanel implements ActionListener {
    ...
    @Override
    public void actionPerformed(ActionEvent e) {
        Object sumber = e.getSource();     // tombol mana yang diklik
        ...
    }
}

// 2. Event Adapter Class: MouseAdapter (hanya override method yang dibutuhkan)
txtLog.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            txtLog.setText("");
        }
    }
});
```

## Cara menggunakan aplikasi

1. Isi **Nama** dan pilih **Jenis** (Manusia atau Hewan).
2. Klik **Berjalan**, **Terbang**, atau **Berenang**. Hasilnya muncul di log.
3. Centang **Hapus log setiap aksi** jika ingin log dibersihkan sebelum tiap aksi.
4. **Klik dua kali** pada area log untuk menghapus isinya.

## Screenshot

### 1. Tampilan awal
![Tampilan Awal](screenshots/01-tampilan-awal.png)

### 2. Jenis Hewan: tombol Berjalan, Terbang, dan Berenang
![Hewan](screenshots/02-hewan.png)

### 3. Jenis Manusia menekan Terbang: dialog informasi
![Manusia Terbang](screenshots/03-manusia-terbang.png)

### 4. Nama kosong: dialog peringatan
![Nama Kosong](screenshots/04-nama-kosong.png)
