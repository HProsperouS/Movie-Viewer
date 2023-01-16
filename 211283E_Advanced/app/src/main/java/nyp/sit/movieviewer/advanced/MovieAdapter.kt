package nyp.sit.movieviewer.advanced

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import nyp.sit.movieviewer.advanced.entity.MovieItem

class MovieAdapter(
    context: Context,
    private val resource : Int = R.layout.card_items_movie,
    objects: MutableList<MovieItem>
) : ArrayAdapter<MovieItem>(context, resource, objects){
    private val mContext: Context

    init {
        mContext = context
    }

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: inflater.inflate(R.layout.card_items_movie, parent, false)

        val movie = getItem(position)

        val titleTextView = rowView.findViewById(R.id.name) as TextView
        titleTextView.text = movie?.title

        val posterImageView = rowView.findViewById(R.id.image) as ImageView
        Picasso.get().load("https://image.tmdb.org/t/p/original/${movie?.poster_path}").resizeDimen(R.dimen.poster_width, R.dimen.poster_height).into(posterImageView)

        return rowView
    }

}

class FavoriteMovieAdapter(context: Context, resource: Int, items: List<FavoriteMovie.MovieItems>) :
    ArrayAdapter<FavoriteMovie.MovieItems>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)

        // Inflate the custom layout for the list item
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.card_items_movie, parent, false)

        // Bind the data from the item to the views in the layout
        val titleTextView = view.findViewById<TextView>(R.id.name)
        titleTextView.text = item?.title

        val posterImageView = view.findViewById<ImageView>(R.id.image)
        Picasso.get().load("https://image.tmdb.org/t/p/original/${item?.poster_path}").resizeDimen(R.dimen.poster_width, R.dimen.poster_height).into(posterImageView)

        return view
    }
}