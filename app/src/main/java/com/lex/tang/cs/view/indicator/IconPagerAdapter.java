package com.lex.tang.cs.view.indicator;

/**
 * Created by tang on 2016/4/4.
 */
public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
