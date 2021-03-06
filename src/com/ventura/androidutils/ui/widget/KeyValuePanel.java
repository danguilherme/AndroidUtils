package com.ventura.androidutils.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ventura.androidutils.R;

public class KeyValuePanel extends LinearLayout {

	TextView key, value;

	private final String DEFAULT_KEY_DELIMITER = ":";

	private String keyText, valueText;
	private String keyDelimiter = DEFAULT_KEY_DELIMITER;

	public KeyValuePanel(Context context, AttributeSet attrs) {
		super(context, attrs);
        inflate(getContext(), R.layout.key_value_panel, this);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.KeyValuePanel);

		keyDelimiter = typedArray
				.getString(R.styleable.KeyValuePanel_keyDelimiter);
		if (keyDelimiter == null) {
			keyDelimiter = DEFAULT_KEY_DELIMITER;
		}
		keyText = typedArray.getString(R.styleable.KeyValuePanel_keyText);
		valueText = typedArray.getString(R.styleable.KeyValuePanel_valueText);

		typedArray.recycle();
		this.initComponent(keyText, valueText, keyDelimiter);
	}

	public KeyValuePanel(Context context, String key, String value) {
		super(context);
        inflate(getContext(), R.layout.key_value_panel, this);
		this.initComponent(key, value, DEFAULT_KEY_DELIMITER);
	}

	public KeyValuePanel(Context context, String key, String value,
			String keyDelimiter) {
		super(context);
        inflate(getContext(), R.layout.key_value_panel, this);
		this.initComponent(key, value, keyDelimiter);
	}

	private void initComponent(String key, String value, String keyDelimiter) {
		this.value = ((TextView) findViewById(R.id.value));
		this.key = ((TextView) findViewById(R.id.key));

		this.keyDelimiter = keyDelimiter;
		this.setKeyValue(key, value);
	}

	public void setViewData() {
		this.setKeyValue(keyText, valueText);
	}

	public String getKey() {
		return keyText;
	}

	public String getValue() {
		return valueText;
	}

	public void setKey(String key) {
		keyText = key;

		if (this.key != null) {
			this.key.setText(keyText + this.keyDelimiter);
		}
	}

	public void setValue(String value) {
		valueText = value;

		if (this.value != null) {
			this.value.setText(valueText);
		}
	}

	public void setKeyValue(String key, String value) {
		this.setKey(key);
		this.setValue(value);
	}

	public void setKeyDelimiter(String keyDelimiter) {
		this.keyDelimiter = keyDelimiter;
	}
}
