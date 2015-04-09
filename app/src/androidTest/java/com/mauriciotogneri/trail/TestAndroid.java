package com.mauriciotogneri.trail;

import android.test.AndroidTestCase;
import android.text.TextUtils;

public class TestAndroid extends AndroidTestCase
{
    public void testEmptyString()
    {
        String nullString = null;
        String blankString = "";
        String spaceString = " ";

        assertEquals(true, TextUtils.isEmpty(nullString));
        assertEquals(true, TextUtils.isEmpty(blankString));
        assertEquals(false, TextUtils.isEmpty(spaceString));
    }
}