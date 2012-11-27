package com.retrofit.android.test;

import java.util.List;
import javax.inject.Named;
import org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;
import retrofit.http.GET;
import retrofit.http.GsonConverter;
import retrofit.http.RestAdapter;
import retrofit.http.Server;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new TwitterDemoTask().execute();
	}

	class TwitterDemoTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			// Create a very simple REST adapter which points the Twitter API
			// endpoint.
			RestAdapter restAdapter = new RestAdapter.Builder()
					.setServer(new Server(API_URL))
					.setClient(new DefaultHttpClient())
					.setConverter(new GsonConverter(new Gson())).build();

			// Create an instance of our Twitter API interface.
			Twitter twitter = restAdapter.create(Twitter.class);

			// Fetch and print a list of the 20 most recent tweets for a user.
			List<Tweet> tweets = twitter.tweets("horse_ebooks");
			for (Tweet tweet : tweets) {
				// System.out.println(tweet.text);
				Log.d(getClass().getName(), tweet.text);
			}

			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private static final String API_URL = "https://api.twitter.com/1/";

	class Tweet {
		String text;
	}

	interface Twitter {
		@GET("statuses/user_timeline.json")
		List<Tweet> tweets(@Named("screen_name") String user);
	}

}
