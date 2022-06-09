package com.example.mahmudtoirovsherlari.activitties

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mahmudtoirovsherlari.MainActivity
import com.example.mahmudtoirovsherlari.R
import com.example.mahmudtoirovsherlari.database.AppDatabase
import com.example.mahmudtoirovsherlari.databinding.ActivityInsertingBinding
import com.example.mahmudtoirovsherlari.models.AudioPoem
import com.example.mahmudtoirovsherlari.models.Book
import com.example.mahmudtoirovsherlari.models.Poem
import com.example.mahmudtoirovsherlari.models.Resource
import com.example.mahmudtoirovsherlari.repository.AudioPoemRepository
import com.example.mahmudtoirovsherlari.repository.BookRepository
import com.example.mahmudtoirovsherlari.repository.PoemRepository

class InsertingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertingBinding
    private lateinit var database: AppDatabase
    private lateinit var bookRepository: BookRepository
    private lateinit var audioPoemRepository: AudioPoemRepository
    private lateinit var poemRepository: PoemRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInsertingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getInstance(this)
        bookRepository = BookRepository(database)
        audioPoemRepository = AudioPoemRepository(database)
        poemRepository = PoemRepository(database)

        setClicks()

        saveAll()

    }

    override fun onResume() {
        super.onResume()
        val handler = Handler()
        val delay: Long = 3000
        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }, delay)
    }

    private fun setClicks() {

        binding.saveAllBooks.setOnClickListener {
            saveBooks()
            binding.saveAllBooks.isEnabled = false
        }
        binding.saveAllPoems.setOnClickListener {
            savePoems()
            binding.saveAllPoems.isEnabled = false
        }

        binding.saveAllAudioPoems.setOnClickListener {
            saveAudioPoems()
            binding.saveAllAudioPoems.isEnabled = false
        }
    }

    private fun saveAll() {
        saveBooks()
        saveAudioPoems()
        savePoems()
    }


    private fun saveBooks() {

        val book1 = Book()
        book1.imageId = R.drawable.book_1
        book1.name = "So‘z saodati"
        book1.year = "2021"
        book1.pages = 176
        bookRepository.saveBook(book1)

        val book2 = Book()
        book2.imageId = R.drawable.book_2
        book2.name = "Yangi she’rlar"
        book2.year = "2018"
        book2.pages = 312
        bookRepository.saveBook(book2)

        val book3 = Book()
        book3.imageId = R.drawable.book_3
        book3.name = "Qutlug‘ yillar sadosi"
        book3.year = "2017"
        book3.pages = 160
        bookRepository.saveBook(book3)

        val book4 = Book()
        book4.imageId = R.drawable.book_4
        book4.name = "Armon tuni"
        book4.year = "2016"
        book4.pages = 128
        bookRepository.saveBook(book4)

        val book5 = Book()
        book5.imageId = R.drawable.book_default
        book5.name = "Ayt endi yuragim"
        book5.year = "1991"
        book5.pages = -1
        bookRepository.saveBook(book5)


        val book6 = Book()
        book6.imageId = R.drawable.book_default
        book6.name = "Biz nechun uchrashdik"
        book6.year = "1992"
        book6.pages = -1
        bookRepository.saveBook(book6)

        val book7 = Book()
        book7.imageId = R.drawable.book_default
        book7.name = "Muhabbat shevasi"
        book7.year = "1995"
        book7.pages = -1
        bookRepository.saveBook(book7)


        val book8 = Book()
        book8.imageId = R.drawable.book_default
        book8.name = "Oqib ketgan armonlar"
        book8.year = "1996"
        book8.pages = -1
        bookRepository.saveBook(book8)


        val book9 = Book()
        book9.imageId = R.drawable.book_default
        book9.name = "Barmoqlar bodasi"
        book9.year = "1998"
        book9.pages = -1
        bookRepository.saveBook(book9)


        val book10 = Book()
        book10.imageId = R.drawable.book_default
        book10.name = "Haqni tanib"
        book10.year = "1999"
        book10.pages = -1
        bookRepository.saveBook(book10)


        val book11 = Book()
        book11.imageId = R.drawable.book_default
        book11.name = "Yuz toʻrtlik"
        book11.year = "2002"
        book11.pages = -1
        bookRepository.saveBook(book11)


        val book12 = Book()
        book12.imageId = R.drawable.book_default
        book12.name = "Saylanma"
        book12.year = "2002"
        book12.pages = -1
        bookRepository.saveBook(book12)

        val book13 = Book()
        book13.imageId = R.drawable.book_default
        book13.name = "Ogoh boʻl, dunyo"
        book13.year = "2005"
        book13.pages = -1
        bookRepository.saveBook(book13)

        val book14 = Book()
        book14.imageId = R.drawable.book_default
        book14.name = "Otamning oʻkinchi"
        book14.year = "2006"
        book14.pages = -1
        bookRepository.saveBook(book14)


        val book15 = Book()
        book15.imageId = R.drawable.book_default
        book15.name = "Sheʼriy hikmat"
        book15.year = "2008"
        book15.pages = -1
        bookRepository.saveBook(book15)

        val book16 = Book()
        book16.imageId = R.drawable.book_default
        book16.name = "Yangi Toshkentnoma"
        book16.year = "20012"
        book16.pages = -1
        bookRepository.saveBook(book16)


        val book17 = Book()
        book17.imageId = R.drawable.book_default
        book17.name = "Bedorlarga bering dunyoni"
        book17.year = "20012"
        book17.pages = -1
        bookRepository.saveBook(book17)
    }

    private fun savePoems() {

        val poem1 = Poem()
        poem1.name = Resource.poemName1
        poem1.poemContext = Resource.poem1
        poem1.book = "FIRST BOOK"
        poemRepository.savePoem(poem1)

        val poem2 = Poem()
        poem2.name = Resource.poemName2
        poem2.poemContext = Resource.poem2
        poem2.book = "FIRST BOOK"
        poemRepository.savePoem(poem2)

        val poem3 = Poem()
        poem3.name = Resource.poemName3
        poem3.poemContext = Resource.poem3
        poem3.book = "FIRST BOOK"
        poemRepository.savePoem(poem3)


        val poem4 = Poem()
        poem4.name = Resource.poemName4
        poem4.poemContext = Resource.poem4
        poem4.book = "FIRST BOOK"
        poemRepository.savePoem(poem4)

        val poem5 = Poem()
        poem5.name = Resource.poemName5
        poem5.poemContext = Resource.poem5
        poem5.book = "FIRST BOOK"
        poemRepository.savePoem(poem5)

        val poem6 = Poem()
        poem6.name = Resource.poemName6
        poem6.poemContext = Resource.poem6
        poem6.book = "FIRST BOOK"
        poemRepository.savePoem(poem6)

        val poem7 = Poem()
        poem7.name = Resource.poemName7
        poem7.poemContext = Resource.poem7
        poem7.book = "FIRST BOOK"
        poemRepository.savePoem(poem7)

        val poem8 = Poem()
        poem8.name = Resource.poemName8
        poem8.poemContext = Resource.poem8
        poem8.book = "FIRST BOOK"
        poemRepository.savePoem(poem8)

        val poem9 = Poem()
        poem9.name = Resource.poemName9
        poem9.poemContext = Resource.poem9
        poem9.book = "FIRST BOOK"
        poemRepository.savePoem(poem9)

        val poem10 = Poem()
        poem10.name = Resource.poemName10
        poem10.poemContext = Resource.poem10
        poem10.book = "FIRST BOOK"
        poemRepository.savePoem(poem10)

        val poem11 = Poem()
        poem11.name = Resource.poemName11
        poem11.poemContext = Resource.poem11
        poem11.book = "FIRST BOOK"
        poemRepository.savePoem(poem11)

        val poem12 = Poem()
        poem12.name = Resource.poemName12
        poem12.poemContext = Resource.poem12
        poem12.book = "FIRST BOOK"
        poemRepository.savePoem(poem12)

        val poem13 = Poem()
        poem13.name = Resource.poemName13
        poem13.poemContext = Resource.poem13
        poem13.book = "FIRST BOOK"
        poemRepository.savePoem(poem13)

        val poem14 = Poem()
        poem14.name = Resource.poemName14
        poem14.poemContext = Resource.poem14
        poem14.book = "FIRST BOOK"
        poemRepository.savePoem(poem14)

        val poem15 = Poem()
        poem15.name = Resource.poemName15
        poem15.poemContext = Resource.poem15
        poem15.book = "FIRST BOOK"
        poemRepository.savePoem(poem15)

        val poem16 = Poem()
        poem16.name = Resource.poemName16
        poem16.poemContext = Resource.poem16
        poem16.book = "FIRST BOOK"
        poemRepository.savePoem(poem16)

        val poem17 = Poem()
        poem17.name = Resource.poemName17
        poem17.poemContext = Resource.poem17
        poem17.book = "FIRST BOOK"
        poemRepository.savePoem(poem17)

        val poem18 = Poem()
        poem18.name = Resource.poemName1
        poem18.poemContext = Resource.poem1
        poem18.book = "FIRST BOOK"
        poemRepository.savePoem(poem18)

        val poem19 = Poem()
        poem19.name = Resource.poemName19
        poem19.poemContext = Resource.poem19
        poem19.book = "FIRST BOOK"
        poemRepository.savePoem(poem19)

        val poem20 = Poem()
        poem20.name = Resource.poemName20
        poem20.poemContext = Resource.poem20
        poem20.book = "FIRST BOOK"
        poemRepository.savePoem(poem20)

        val poem21 = Poem()
        poem21.name = Resource.poemName21
        poem21.poemContext = Resource.poem21
        poem21.book = "FIRST BOOK"
        poemRepository.savePoem(poem21)

        val poem22 = Poem()
        poem22.name = Resource.poemName22
        poem22.poemContext = Resource.poem22
        poem22.book = "FIRST BOOK"
        poemRepository.savePoem(poem22)

        val poem23 = Poem()
        poem23.name = Resource.poemName2
        poem23.poemContext = Resource.poem2
        poem23.book = "FIRST BOOK"
        poemRepository.savePoem(poem23)

        val poem24 = Poem()
        poem24.name = Resource.poemName24
        poem24.poemContext = Resource.poem24
        poem24.book = "FIRST BOOK"
        poemRepository.savePoem(poem24)

        val poem25 = Poem()
        poem25.name = Resource.poemName25
        poem25.poemContext = Resource.poem25
        poem25.book = "FIRST BOOK"
        poemRepository.savePoem(poem25)

    }

    private fun saveAudioPoems() {

        val poem1 = AudioPoem()
        poem1.audioID = R.raw.poem1
        poem1.name = "G'zallikdan ko'zlar yashnagay (Dilnoza Kubayeva)"
        audioPoemRepository.savePoem(poem1)

        val poem2 = AudioPoem()
        poem2.audioID = R.raw.poem_2
        poem2.name = "Uyda qoling"
        audioPoemRepository.savePoem(poem2)

        val poem3 = AudioPoem()
        poem3.audioID = R.raw.poem_3
        poem3.name = "Omonlik (Erkin Komilov)"
        audioPoemRepository.savePoem(poem3)

        val poem4 = AudioPoem()
        poem4.audioID = R.raw.poem_4
        poem4.name = "Kitob kerakmi"
        audioPoemRepository.savePoem(poem4)

        val poem5 = AudioPoem()
        poem5.audioID = R.raw.poem_5
        poem5.name = "Baxt"
        audioPoemRepository.savePoem(poem5)

        val poem6 = AudioPoem()
        poem6.audioID = R.raw.poem_6
        poem6.name = "Otam nasixati (Erkin Komilov)"
        audioPoemRepository.savePoem(poem6)

        val poem7 = AudioPoem()
        poem7.audioID = R.raw.poem_7
        poem7.name = "Sizga barbirmi (Parizoda)"
        audioPoemRepository.savePoem(poem7)

    }
}