# MONSTER SAKU
# IF2212 Pemrograman Berorientasi Objek
## Tugas Besar Kelompok 18 - GWS

### Deskripsi Program
Game Monster Saku ini merupakan game yang dapat dimainkan oleh 2 orang pemain. Setiap pemain mendapatkan 6 monster secara random. Lalu 1 monster random dipilih untuk menjadi current monster tiap pemain. Setiap monster memiliki beberapa moves, stats, dan element types masing-masing. 

Terdapat empat jenis move dari masing-masing monster yaitu normal move, special move, default move, dan status move. Masing-masing move memiliki ammunition yang menandakan berapa kali move dapat digunakan. Default move memiliki ammunition tidak terbatas dan selalu dapat digunakan. Status move dapat menyebabkan monster (baik sendiri maupun monster lawan) mengalami suatu status condition tertentu yang berpengaruh pada stats monster atau perubahan pada stats buff. Terdapat juga element type masing-masing monster dan move yang memiliki efektivitas penyerangan masing-masing.

Saat giliran pemain, pemain dapat memilih 1 dari 2 battle commands, yaitu memilih move dari current monster untuk ditandingkan dengan current monster lawan atau mengganti current monster dengan monster lain yang masih hidup. 

Jika kedua pemain memilih move, maka kedua move akan dibandingkan priority-nya. Priority move yang lebih besar akan bergerak lebih dahulu. Jika priority-nya sama, maka move yang lebih dahulu dieksekusi adalah monster dengan speed yang lebih tinggi. Jika speed kedua monster sama, maka akan di-random urutannya. Jika salah satu pemain memilih untuk switch current monster dan yang lainnya memilih move, maka current monster akan ditukar terlebih dahulu lalu dilakukan move. Jika kedua pemain memilih untuk switch current monster, maka kedua current monster pemain akan berganti dan langsung bergerak ke turn selanjutnya. Permainan akan berakhir dan dimenangkan pemain jika semua monster dari pemain lawan sudah mati.

### Anggota Kelompok 18 - GWS
###### 18219046 - Michael Ryan Martin 
###### 18220038 - Akmal Jauhar Sidqi
###### 18220084 - Dewa Ayu Mutiara Kirana P D
###### 18220104 - Gresya Angelina E Leman

### Cara Kompilasi Program :
1. Download dan set up compiler java pada device yang akan digunakan.
2. Clone repository ini.
3. Selain melakukan clone repository, folder repository ini dapat di-download dalam bentuk file.zip, lalu di-extract ke suatu folder pada device yang digunakan untuk menjalankan program.
4. Dalam menjalankan program ini, terdapat 3 buah file konfigurasi, yaitu element-type-effectivity-chart.csv, monsterpool.csv, dan movepool.csv, yang dapat diakses pada ...\IF2212-Monster-Saku-Game\src\main\resources\com\monstersaku\configs

Note : file konfigurasi dapat diubah-ubah sesuai kebutuhan.