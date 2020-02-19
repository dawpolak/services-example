package com.example.musicplayer

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private lateinit var mediaPlayer : MediaPlayer
    //private var isPlay : Boolean = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.sample)
        if(!mediaPlayer.isPlaying) {
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

            val notification = NotificationCompat.Builder(this, "TestService")
                .setContentTitle("Song player")
                .setContentText("Your favourite song..")
                .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                .setContentIntent(pendingIntent)
                .build()


            mediaPlayer.start()

            startForeground(1, notification)


        }
        return Service.START_NOT_STICKY


    }

    override fun onDestroy() {
        mediaPlayer.stop()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}