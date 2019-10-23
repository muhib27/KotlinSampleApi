package com.e.kotlinsampleapi

import android.os.Bundle


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
//https://stackoverflow.com/questions/45219379/how-to-make-an-api-request-in-kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter : MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter = MoviesAdapter()
        movies_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        movies_list.adapter = movieAdapter

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val apiMovies = retrofit.create(ApiMovies::class.java)

        apiMovies.getMovies()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieAdapter.setMovies(it.data) },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })

        KotlinApiClient.callApi().addFcm("dfdf")
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Demo(it.status.code.toString(), it.status.msg.toString())
            },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })
    }

    fun Demo(code: String, msg: String){
        Toast.makeText(applicationContext, msg + " " + code, Toast.LENGTH_SHORT).show()
    }

    inner class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

        private val movies: MutableList<Movie> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            return MovieViewHolder(layoutInflater.inflate(R.layout.item_row, parent, false))
        }

        override fun getItemCount(): Int {
            return movies.size
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            holder.bindModel(movies[position])
        }

        fun setMovies(data: List<Movie>) {
            movies.addAll(data)
            notifyDataSetChanged()
        }

        inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val movieTitleTxt : TextView = itemView.findViewById(R.id.movieTitle)
            val movieGenreTxt : TextView = itemView.findViewById(R.id.movieGenre)
            val movieYearTxt : TextView = itemView.findViewById(R.id.movieYear)
            val movieAvatarImage : ImageView = itemView.findViewById(R.id.movieAvatar)

            fun bindModel(movie: Movie) {
                movieTitleTxt.text = movie.title
                movieGenreTxt.text = movie.genre
                movieYearTxt.text = movie.year
                Picasso.get().load(movie.poster).into(movieAvatarImage)
            }
        }
    }
}
