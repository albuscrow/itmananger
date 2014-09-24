package com.itmanapp2.util;

import android.text.Html;
import android.text.Spanned;
import android.text.SpannedString;

public class CommonUtil {

	//add by albuscrow
	static public Spanned decorateStringWithUnderlineAndColor(String input){
		return Html.fromHtml("<font color='blue'> <u>" + input + "</u> </font>");
	}}
