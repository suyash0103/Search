package com.example.android.search;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.ImageSpan;
import android.util.TypedValue;
import android.widget.SearchView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public SharedPreferences searchPrefs;
    ArrayAdapter searchAdapter;
    ArrayList<String> historyList, responseList;
    LinearLayout main, parent;
    DisplayMetrics displayMetrics;
    float remainingWidth = 0;
    String search;
    TextView mTextSample;
    ListView listView;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Hello", "Hello");

        menu = (Menu) findViewById(R.id.menuSearch);
        main = (LinearLayout) findViewById(R.id.main_view);
        parent = (LinearLayout) findViewById(R.id.parent);
        listView = (ListView) findViewById(R.id.searchId);

        parent.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);

        searchPrefs = getApplicationContext().getSharedPreferences("Searches", MODE_PRIVATE);
        SharedPreferences.Editor edit = searchPrefs.edit();

        mTextSample = (TextView) findViewById(R.id.text);
        String text = "Flipkart     Jabongjjjjjj     Makemytrip     Myntra     Indiadaag     iiindia     leststst     koaosoaos     poasslsoamas     hhjjvhhghgh     kjkygfhgfhg     jhgjghghg";
        makeTagLinks(text, mTextSample);

        // searchText = findViewById(R.id.menuSearch);

//        displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        float width = displayMetrics.widthPixels;
//        remainingWidth = width;
//        Log.v("Width of screen", Float.toString(width));

        int size = searchPrefs.getAll().size();

        if (size > 0) {
//            edit.putString(Integer.toString(size), search.toString());
//            edit.commit();
//            final ArrayList<String> list = new ArrayList<>();
//            Map<String, ?> entries = searchPrefs.getAll();
//            Set<String> keys = entries.keySet();
//            for(String key : keys)
//            {
//                list.add(searchPrefs.getString(key, ""));
//            }
        }

        historyList = new ArrayList<>();
        edit.putString("1", "book");
        edit.putString("2", "car");
        edit.putString("3", "pen");
        edit.putString("4", "bike1");
        edit.putString("5", "bike2");
        edit.putString("6", "bike3");
        edit.putString("7", "bike4");
        edit.putString("8", "bik5");
        edit.putString("9", "bike6");
        edit.putString("10", "bike7");
        edit.putString("11", "bike8");
        edit.putString("12", "bike9");
        edit.putString("13", "bike10");
        edit.commit();
//        historyList.add(searchPrefs.getString("1", ""));
//        historyList.add(searchPrefs.getString("2", ""));
//        historyList.add(searchPrefs.getString("3", ""));
//        historyList.add(searchPrefs.getString("4", ""));


//        int n = 13;
//        for (int i = 0; i < n; i++) {
//            LinearLayout ll = (LinearLayout) findViewById(R.id.historyList);
//
//            int k = i + 1;
//            final TextView tv = new TextView(this);
//            String toadd = searchPrefs.getString(Integer.toString(k), "");
//            Rect bounds = new Rect();
//            Paint textPaint = tv.getPaint();
//            textPaint.getTextBounds(toadd, 0, toadd.length(), bounds);
//            float widthString = bounds.width();
//
////            if(remainingWidth > 800)
////            {
//            tv.setText(searchPrefs.getString(Integer.toString(k), ""));
////                remainingWidth -= widthString;
////                Log.v("remainingWidth", Float.toString(remainingWidth));
////            }
////            else
////            {
////                remainingWidth = width;
////                tv.setText("\n" + searchPrefs.getString(Integer.toString(k), ""));
////                remainingWidth -= widthString;
////                Log.v("remainingWidth with 'n'", Float.toString(remainingWidth));
////            }
//            tv.setId(i + 5);
//            tv.setTextSize(20);
//
//
//            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            llp.setMargins(0, 10, 50, 0); // llp.setMargins(left, top, right, bottom);
//            tv.setLayoutParams(llp);
//
//
//            tv.setMovementMethod(new LinkTouchMovementMethod());
//            tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.presence_offline, 0);
//
//            //Making Bubble
//            BitmapDrawable bd = (BitmapDrawable) convertViewToDrawable(tv);
//            bd.setBounds(0, 0, bd.getIntrinsicWidth(), bd.getIntrinsicHeight());
//            tv.setBackgroundResource(R.drawable.bubble);
//            tv.setMovementMethod(LinkMovementMethod.getInstance());
//
//            ll.addView(tv);
//
////            final SpannableStringBuilder sb = new SpannableStringBuilder();
////            ClickableSpan clickSpan = new ClickableSpan() {
////
////                @Override
////                public void onClick(View view) {
////                    Log.v("clicked", view.getClass().getSimpleName());
////
////                }
////
////            };
//        }

        // searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);

        final ListView listView = (ListView) findViewById(R.id.searchId);
        // listView.setAdapter(searchAdapter);

        findViewById(R.id.clearHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyList.clear();
                SharedPreferences searches = getSharedPreferences("Searches", MODE_PRIVATE);
                SharedPreferences.Editor editor = searches.edit();
                editor.clear();
                // searchAdapter.clear();
                listView.setAdapter(null);
                LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
                LinearLayout layout = (LinearLayout) findViewById(R.id.main_view);
                parent.removeView(layout);
            }
        });

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                parent.setVisibility(View.VISIBLE);
//            }
//        });

//        TextWatcher textWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String editText = searchText.getText().toString();
//                if (TextUtils.isEmpty(editText)) {
//                    ListView listView = (ListView) findViewById(R.id.searchId);
//                    ArrayAdapter searchAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, historyList);
//                    listView.setAdapter(searchAdapter);
//                    Log.v("Empty edit text", "Empty edit text");
//                    // main.setVisibility(View.VISIBLE);
//                } else {
//                    // main.setVisibility(View.INVISIBLE);
//                    responseList = new ArrayList<>();
//                    responseList.add("Travel");
//                    responseList.add("TravelKhana");
//                    responseList.add("Travel VISA");
//                    responseList.add("Travel XP");
//                    responseList.add("TravelMob");
//                    responseList.add("TravelEasy");
//                    responseList.add("RiyaTravels");
//                    searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, responseList);
//                    ListView listView = (ListView) findViewById(R.id.searchId);
//                    listView.setAdapter(searchAdapter);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        };
//        searchText.addTextChangedListener(textWatcher);
    }

    public static Object convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();
        return new BitmapDrawable(viewBmp);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);

//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                parent.setVisibility(View.VISIBLE);
//                return false;
//            }
//        });

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.w("myApp", "onQueryTextSubmit ");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(s)) {
                    ListView listView = (ListView) findViewById(R.id.searchId);
                    ArrayAdapter searchAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, historyList);
                    listView.setAdapter(searchAdapter);
                    Log.v("Empty edit text", "Empty edit text");
                    parent.setVisibility(View.VISIBLE);
                } else {
                    parent.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    responseList = new ArrayList<>();
                    responseList.add("Travel");
                    responseList.add("TravelKhana");
                    responseList.add("Travel VISA");
                    responseList.add("Travel XP");
                    responseList.add("TravelMob");
                    responseList.add("TravelEasy");
                    responseList.add("RiyaTravels");
                    searchAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, responseList);
                    ListView listView = (ListView) findViewById(R.id.searchId);
                    listView.setAdapter(searchAdapter);
                }
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void makeTagLinks(final String text, final TextView tv) {
        if (text == null || tv == null) {
            return;
        }
        final List<String> items = Arrays.asList(text.split("     "));

        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26); // Tricking the text view for getting a bigger line height
        final SpannableStringBuilder ss = new SpannableStringBuilder();

        int start = 0, end;
        int height = tv.getHeight();
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.rounded_corner);
//        int width = height * drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
//        drawable.setBounds(0, 0, width, height);
        float textSize = 11 * getResources().getDisplayMetrics().scaledDensity; // sp to px


        for (final String item : items) {
            end = start + item.length();
            if (start < end) {
                ss.append(item);
                ss.append("     ");
                Drawable d = getResources().getDrawable(R.drawable.ic_restore_black_24dp);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
                ImageSpan imageSpan = new ImageSpan(getResources().getDrawable(R.drawable.ic_restore_black_24dp), ImageSpan.ALIGN_BOTTOM);
                RoundedBackgroundSpan2 tagSpan = new RoundedBackgroundSpan2(Color.GRAY, Color.BLACK, textSize);

                ss.setSpan(new RoundedBackgroundSpan2(Color.GRAY, Color.BLACK, textSize), start, end, 0);
                ss.setSpan(new MyClickableSpan(item), start, end, 0);
            }

            start += item.length() + 5;//comma and space in the original text ;)
        }

        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setTransformationMethod(null);

        tv.setText(ss, TextView.BufferType.SPANNABLE);
    }

    private class MyClickableSpan extends ClickableSpan {
        private final String mText;

        private MyClickableSpan(final String text) {
            mText = text;

        }

        @Override
        public void onClick(final View widget) {
            Log.wtf("You Clicked", mText);
            //mListener.onTagClicked(mText);
        }
    }

}