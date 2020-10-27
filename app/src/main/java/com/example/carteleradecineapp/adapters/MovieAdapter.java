package com.example.carteleradecineapp.adapters;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carteleradecineapp.MainActivity;
import com.example.carteleradecineapp.R;
import com.example.carteleradecineapp.models.MovieInfo;
import com.example.carteleradecineapp.models.MoviesList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    MoviesList moviesLists;
    ArrayList<MovieInfo> movieInfoList=new ArrayList<>();
    ArrayList<String> funcionList=new ArrayList<>();
    Context context;
    String movieTitle,funcionMovie;
    private static final String CHANNEL_ID = "Canal de Cartelera";

    public MovieAdapter(MoviesList moviesLists, Context context) {
        this.moviesLists = moviesLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview= LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        movieInfoList=moviesLists.getResults();
        funcionMovie=holder.txtFuncion.getText().toString();
        funcionList.add(funcionMovie);
        holder.txtNameMovie.setText(movieInfoList.get(position).getTitle());
        holder.txtOverviewMovie.setText(movieInfoList.get(position).getOverview());
        String urlPosterPath="https://image.tmdb.org/t/p/w500"+movieInfoList.get(position).getPoster_path();
        Picasso.with(context).load(urlPosterPath).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return moviesLists.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameMovie,txtOverviewMovie,txtFuncion;
        ImageView imgMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameMovie=itemView.findViewById(R.id.txtNameMovie);
            txtOverviewMovie=itemView.findViewById(R.id.txtOverviewMovie);
            txtFuncion=itemView.findViewById(R.id.txtFuncion);
            imgMovie=itemView.findViewById(R.id.imgMovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getLayoutPosition();
                    movieTitle=movieInfoList.get(position).getTitle();
                    funcionMovie=funcionList.get(position);
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        createNotificationChannel(CHANNEL_ID, "Canal de Cartelera");
                    }

                    String texto="Usted reservo un tiecket para la siguiente pelicula:\n\n"+movieTitle+"\n"+funcionMovie;
                    showNotification(carteleraNotification(context,CHANNEL_ID,texto),position+1);
                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String id, String name){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.createNotificationChannel(new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH));
        }
    }

    private void showNotification(Notification notification, int id){
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(id, notification);
    }

    public static Notification carteleraNotification(Context context, String CHANNEL_ID,String texto){

        Intent resultIntent = new Intent(context, MainActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(context, 0,
                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,CHANNEL_ID)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setColor(Color.parseColor("#71b32a"))
                        .setContentTitle("Cartelera de Cine")
                        .setContentText(texto)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
        return builder.build();
    }
}
