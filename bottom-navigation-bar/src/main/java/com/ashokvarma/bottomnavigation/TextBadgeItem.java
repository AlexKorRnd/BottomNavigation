package com.ashokvarma.bottomnavigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @see BadgeItem
 * @since 23 Jun 2017
 */
public class TextBadgeItem extends BadgeItem<TextBadgeItem> {

    private int mBackgroundColorResource;
    private String mBackgroundColorCode;
    private int mBackgroundColor = Color.RED;

    private int mTextColorResource;
    private String mTextColorCode;
    private int mTextColor = Color.WHITE;

    private CharSequence mText;

    private int mBorderColorResource;
    private String mBorderColorCode;
    private int mBorderColor = Color.WHITE;

    private int mBorderWidthInPixels = 0;

    private int radius = 0;

    ///////////////////////////////////////////////////////////////////////////
    // Public setter methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param colorResource resource for background color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBackgroundColorResource(@ColorRes int colorResource) {
        this.mBackgroundColorResource = colorResource;
        refreshDrawable();
        return this;
    }

    /**
     * @param colorCode color code for background color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBackgroundColor(@Nullable String colorCode) {
        this.mBackgroundColorCode = colorCode;
        refreshDrawable();
        return this;
    }

    /**
     * @param color background color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBackgroundColor(int color) {
        this.mBackgroundColor = color;
        refreshDrawable();
        return this;
    }

    /**
     * @param colorResource resource for text color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setTextColorResource(@ColorRes int colorResource) {
        this.mTextColorResource = colorResource;
        setTextColor();
        return this;
    }

    /**
     * @param colorCode color code for text color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setTextColor(@Nullable String colorCode) {
        this.mTextColorCode = colorCode;
        setTextColor();
        return this;
    }

    /**
     * @param color text color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setTextColor(int color) {
        this.mTextColor = color;
        setTextColor();
        return this;
    }

    /**
     * @param text text to be set in badge (this shouldn't be empty text)
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setText(@Nullable CharSequence text) {
        this.mText = text;
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            if (!TextUtils.isEmpty(text)) {
                textView.setText(text);
            }
        }
        return this;
    }

    /**
     * @param colorResource resource for border color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBorderColorResource(@ColorRes int colorResource) {
        this.mBorderColorResource = colorResource;
        refreshDrawable();
        return this;
    }

    /**
     * @param radius corner radius
     * @return this, to allow builder pattern
     * */
    public TextBadgeItem setCornerRadius(int radius) {
        this.radius = radius;
        refreshDrawable();
        return this;
    }

    /**
     * @param colorCode color code for border color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBorderColor(@Nullable String colorCode) {
        this.mBorderColorCode = colorCode;
        refreshDrawable();
        return this;
    }

    /**
     * @param color border color
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBorderColor(int color) {
        this.mBorderColor = color;
        refreshDrawable();
        return this;
    }

    /**
     * @param borderWidthInPixels border width in pixels
     * @return this, to allow builder pattern
     */
    public TextBadgeItem setBorderWidth(int borderWidthInPixels) {
        this.mBorderWidthInPixels = borderWidthInPixels;
        refreshDrawable();
        return this;
    }

    /**
    * if @param -1 then no setting
    * */
    public void setMargin(int left, int top, int right, int down) {
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            final ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
            if (left >= 0) {
                params.leftMargin = left;
            }
            if (top >= 0) {
                params.topMargin = top;
            }
            if (right >= 0) {
                params.rightMargin = right;
            }
            if (down >= 0) {
                params.bottomMargin = down;
            }
            textView.setLayoutParams(params);
        }
    }

    public void setTextSize(int textSize) {
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            textView.setTextSize(textSize);
        }
    }

    public void setTextAppearance(int resId) {
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(resId);
            } else {
                textView.setTextAppearance(textView.getContext(), resId);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Library only access method
    ///////////////////////////////////////////////////////////////////////////


    /**
     * {@inheritDoc}
     */
    @Override
    TextBadgeItem getSubInstance() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void bindToBottomTabInternal(BottomNavigationTab bottomNavigationTab) {
        Context context = bottomNavigationTab.getContext();

        GradientDrawable shape = getBadgeDrawable(context);
        bottomNavigationTab.badgeView.setBackgroundDrawable(shape);
        bottomNavigationTab.badgeView.setTextColor(getTextColor(context));
        bottomNavigationTab.badgeView.setText(getText());
    }

    ///////////////////////////////////////////////////////////////////////////
    // Class only access methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * @param context to fetch color
     * @return background color
     */
    private int getBackgroundColor(Context context) {
        if (this.mBackgroundColorResource != 0) {
            return ContextCompat.getColor(context, mBackgroundColorResource);
        } else if (!TextUtils.isEmpty(mBackgroundColorCode)) {
            return Color.parseColor(mBackgroundColorCode);
        } else {
            return mBackgroundColor;
        }
    }

    /**
     * @param context to fetch color
     * @return text color
     */
    private int getTextColor(Context context) {
        if (this.mTextColorResource != 0) {
            return ContextCompat.getColor(context, mTextColorResource);
        } else if (!TextUtils.isEmpty(mTextColorCode)) {
            return Color.parseColor(mTextColorCode);
        } else {
            return mTextColor;
        }
    }

    /**
     * @return text that needs to be set in badge
     */
    private CharSequence getText() {
        return mText;
    }

    /**
     * @param context to fetch color
     * @return border color
     */
    private int getBorderColor(Context context) {
        if (this.mBorderColorResource != 0) {
            return ContextCompat.getColor(context, mBorderColorResource);
        } else if (!TextUtils.isEmpty(mBorderColorCode)) {
            return Color.parseColor(mBorderColorCode);
        } else {
            return mBorderColor;
        }
    }

    /**
     * @return border width
     */
    private int getBorderWidth() {
        return mBorderWidthInPixels;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Internal Methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * refresh's background drawable
     */
    private void refreshDrawable() {
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            textView.setBackgroundDrawable(getBadgeDrawable(textView.getContext()));
        }
    }

    /**
     * set's new text color
     */
    private void setTextColor() {
        if (isWeakReferenceValid()) {
            TextView textView = getTextView().get();
            textView.setTextColor(getTextColor(textView.getContext()));
        }
    }

    /**
     * @param context to fetch color
     * @return return the background drawable
     */
    private GradientDrawable getBadgeDrawable(Context context) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(getRadius(context));
        shape.setColor(getBackgroundColor(context));
        shape.setStroke(getBorderWidth(), getBorderColor(context));
        return shape;
    }

    private int getRadius(Context context) {
        if (radius == 0) {
            return context.getResources().getDimensionPixelSize(R.dimen.badge_corner_radius);
        } else {
            return radius;
        }
    }
}