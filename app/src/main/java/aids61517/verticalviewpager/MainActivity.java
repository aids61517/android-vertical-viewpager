package aids61517.verticalviewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private VerticalViewPager mVerticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        Log.d(TAG, "initView");
        mVerticalViewPager = (VerticalViewPager) findViewById(R.id.view_pager);
        mVerticalViewPager.setPageTransformer(true, new VerticalPageTransformer());
        mVerticalViewPager.setAdapter(new VerticalPageAdapter(this));
    }

    private class VerticalPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            page.setTranslationX(page.getWidth() * -position);
            page.setTranslationY(page.getHeight() * position);
        }
    }

    private class VerticalPageAdapter extends PagerAdapter {
        Context context;

        public VerticalPageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(TAG, "position = " + String.valueOf(position));
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.viewpager_content, container, false);
            TextView textView = (TextView) view.findViewById(R.id.page_position);
            textView.setText(String.valueOf(position));
            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
