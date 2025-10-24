## Alur Data Aplikasi (MVVM & Retrofit)

Aplikasi ini dibangun menggunakan arsitektur **MVVM (Model-View-ViewModel)** dan memanfaatkan **Retrofit** untuk *networking*. Berikut adalah alur data dari pemanggilan API hingga tampil di layar:

## Identitas

* **Nama:** [AYU FITRIANINGSIH]
* **NIM:** H1D023038
* **Shift Baru:** [E]
* **Shift Asal:** [A]
---
### Dokumentasi
* **Klik untuk melihat demo:** [Download Video](https://github.com/ayfitrian/Responsi-1-Mobile-H1D023038/raw/main/app/videos/demoresponsi.mp4)
---

### 1. Pemicu Permintaan Data (View)
* **Kapan:** Saat pengguna membuka layar yang butuh data dari internet (misal, layar Pelatih atau Skuad).
* **Siapa:** `Activity` atau `Fragment` (bagian **View**).
* **Apa:** `View` **tidak** mengambil data sendiri. Ia "memerintahkan" `ViewModel` untuk mulai bekerja.
    * Contoh kode di `Activity`: `viewModel.fetchData()`

---

### 2. Pengambilan Data Dimulai (ViewModel)
* **Siapa:** `TeamViewModel` (bagian **ViewModel**).
* **Apa:**
    1.  Menerima perintah dari `View`.
    2.  Menggunakan `viewModelScope.launch` (Kotlin Coroutines) untuk menjalankan tugas pengambilan data di *background*. Ini penting agar aplikasi tidak "macet" saat menunggu data dari internet.
    3.  Memanggil `RetrofitInstance.api` untuk membuat permintaan HTTP ke server `football-data.org`.

---

### 3. Komunikasi dengan Server (Retrofit)
* **Siapa:** `RetrofitInstance` dan *interface* `FootballApi`.
* **Apa:**
    1.  Retrofit membuat permintaan HTTP `GET` ke *endpoint* `v4/teams/512`.
    2.  Permintaan ini menyertakan *header* `X-Auth-Token` dengan nilai API Key Anda.
    3.  Server `football-data.org` menerima permintaan, memprosesnya, dan mengirimkan kembali data dalam format **JSON** (JavaScript Object Notation).

---

### 4. Mengubah JSON menjadi Objek Kotlin (Gson & Model)
* **Siapa:** Retrofit (dengan bantuan **Gson Converter**) dan **Data Class** (`TeamResponse`, `Coach`, `Player`).
* **Apa:**
    1.  Retrofit menerima respons JSON dari server.
    2.  Gson Converter secara otomatis membaca JSON tersebut dan "mencocokkannya" dengan struktur Data Class (**Model**) yang sudah kita buat. `@SerializedName` membantu pencocokan ini.
    3.  Hasilnya adalah objek Kotlin (`TeamResponse`) yang berisi data pelatih dan pemain, siap digunakan di kode kita.

---

### 5. Memperbarui "Papan Pengumuman" (ViewModel & LiveData)
* **Siapa:** `TeamViewModel`.
* **Apa:**
    1.  Menerima objek `TeamResponse` yang sudah jadi dari Retrofit.
    2.  Memperbarui nilai `MutableLiveData` (`_teamData`) dengan objek `TeamResponse` tersebut. `LiveData` adalah wadah data spesial yang bisa "diobservasi".

---

### 6. Menampilkan Data ke Pengguna (View & Observe)
* **Siapa:** `Activity` atau `Fragment` (bagian **View**).
* **Apa:**
    1.  `View` sudah "berlangganan" (`observe`) ke `LiveData` (`teamData`) di `ViewModel`.
    2.  Ketika nilai `LiveData` di `ViewModel` berubah (karena data baru masuk), `View` akan otomatis diberitahu.
    3.  Kode di dalam blok `observe` di `Activity`/`Fragment` akan dijalankan.
    4.  `View` mengambil data terbaru dari `LiveData` (objek `TeamResponse`).
    5.  Data tersebut kemudian ditampilkan ke komponen UI:
        * Nama pelatih dimasukkan ke `TextView` di `HeadCoachActivity`.
        * Daftar pemain (`teamResponse.squad`) diberikan ke `PlayerAdapter` yang kemudian akan menampilkannya di `RecyclerView` `TeamSquadActivity`.
        * Saat item `RecyclerView` diklik, data pemain yang sesuai dikirim ke `PlayerDetailFragment` untuk ditampilkan di *bottom sheet*.

---

Alur ini memastikan bahwa `View` hanya bertugas menampilkan data, `ViewModel` mengelola data dan logika, dan `Model` merepresentasikan data itu sendiri, sesuai prinsip MVVM.
