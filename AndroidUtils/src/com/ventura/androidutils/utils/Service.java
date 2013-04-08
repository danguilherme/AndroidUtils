package com.ventura.androidutils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;

import com.ventura.androidutils.exception.LazyInternetConnectionException;
import com.ventura.androidutils.exception.NoInternetConnectionException;

public class Service {
	final String TAG = getClass().getName();

	private Context context;

	public Service(Context context) {
		this.context = context;
	}

	private void beforeRequest() throws NoInternetConnectionException {
		if (!ConnectionManager.isConnected(this.getContext()))
			throw new NoInternetConnectionException();
	}

	protected String doGet(String url) throws NoInternetConnectionException,
			LazyInternetConnectionException {

		HttpGet request = null;
		try {
			request = new HttpGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.doGet(request);
	}

	protected String doGet(HttpGet request)
			throws NoInternetConnectionException,
			LazyInternetConnectionException {

		this.beforeRequest();

		DefaultHttpClient httpClient = new DefaultHttpClient();
		request.setHeader("Accept", "application/json");

		Log.i(TAG, "Requesting URL: " + request.getURI());

		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			throw new LazyInternetConnectionException();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (response == null)
			return null;

		Log.i(TAG, "Response statusline: " + response.getStatusLine());
		InputStream data;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			data = response.getEntity().getContent();

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(data));
			String responseLine;

			while ((responseLine = bufferedReader.readLine()) != null) {
				responseBuilder.append(responseLine);
			}
			Log.i(TAG, "Response: " + responseBuilder.toString());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseBuilder.toString();
	}

	public Context getContext() {
		return context;
	}
}
