package com.spaceappsto.spacecalendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class PagerActivity extends Activity {

	private static final int PAGE_LEFT = 0;
	private static final int PAGE_MIDDLE = 1;
	private static final int PAGE_RIGHT = 2;

	private static final int NUM_ITEMS = 3;

	private ViewPager mPager;
	private int mSelectedPageIndex = 1;
	private int middlePageValue = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new CalendarAdapter());
		mPager.setCurrentItem(PAGE_MIDDLE, false);

		mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				mSelectedPageIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE) {
					// user swiped to right direction --> left page
					if (mSelectedPageIndex == PAGE_LEFT) {
						middlePageValue--;

					// user swiped to left direction --> right page
					} else if (mSelectedPageIndex == PAGE_RIGHT) {
						middlePageValue++;
					}
					mPager.setCurrentItem(PAGE_MIDDLE, false);
				}
			}
		});
	}

	private class CalendarAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);
		}

		@Override
		public Object instantiateItem(View container, int position) {

			View pageView = getLayoutInflater().inflate(R.layout.cal_page, null);
			TextView pageText = (TextView) pageView.findViewById(R.id.cal_page_text);

			((ViewPager) container).addView(pageView, 0);

			return pageView;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}
	}
}
