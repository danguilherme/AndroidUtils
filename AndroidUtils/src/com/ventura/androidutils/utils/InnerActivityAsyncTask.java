package com.ventura.androidutils.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

public abstract class InnerActivityAsyncTask<Params, Progress, Result> extends
		AsyncTask<Params, Progress, Result> {
	private ProgressDialog mProgressDialog;
	private String mProgressMessage = null;
	private Context mContext;

	public InnerActivityAsyncTask(Context context, String progressTitle,
			String progressMessage) {
		this.mContext = context;
		try {
			this.mProgressDialog = new ProgressDialog(this.mContext);
			this.mProgressDialog.setTitle(progressTitle);
			this.mProgressDialog.setCancelable(true);
			this.mProgressDialog
					.setOnCancelListener(new DialogInterface.OnCancelListener() {
						@Override
						public void onCancel(DialogInterface progressDialog) {
							onProgressDialogCancelled(mProgressDialog);
						}
					});
			this.setProgressMessage(progressMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setProgressMessage(String message) {
		this.mProgressMessage = message;
		this.mProgressDialog.setMessage(mProgressMessage);
	}

	protected String getProgressMessage() {
		return this.mProgressMessage;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mProgressMessage != null) {
			this.mProgressDialog.show();
		}
	}

	@Override
	protected Result doInBackground(Params... params) {
		return null;
	}

	@Override
	protected void onPostExecute(Result result) {
		super.onPostExecute(result);
		if (this.mProgressMessage != null) {
			this.mProgressDialog.dismiss();
		}
	}

	public Context getContext() {
		return this.mContext;
	}

	public abstract void onProgressDialogCancelled(
			DialogInterface progressDialog);
}
