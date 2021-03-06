package io.bunsan.filmica.films;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.bunsan.filmica.model.Film;
import io.bunsan.filmica.R;

import java.util.ArrayList;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmHolder> {

    private ArrayList<Film> films;
    private OnItemClickListener listener;

    public FilmsAdapter(OnItemClickListener listener) {
        this.films = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film,
                viewGroup, false);

        return new FilmHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder filmHolder, int position) {
        Film film = films.get(position);
        filmHolder.setFilm(film);
    }

    @Override
    public int getItemCount() {
        return this.films.size();
    }

    public void setFilms(ArrayList<Film> films) {
        this.films.clear();
        this.films.addAll(films);

        this.notifyDataSetChanged();
    }

    class FilmHolder extends RecyclerView.ViewHolder {

        private Film film;

        FilmHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FilmsAdapter.this.listener.onItemClick(film);
                }
            });
        }

        void setFilm(Film film) {
            this.film = film;
            ((TextView)this.itemView.findViewById(R.id.label_title)).setText(film.getTitle());
            ((TextView)this.itemView.findViewById(R.id.label_genre)).setText(film.getGenres().get(0).getName());
        }

    }

    interface OnItemClickListener {
        void onItemClick(Film film);
    }
}
