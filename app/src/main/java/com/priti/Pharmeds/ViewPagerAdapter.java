package com.priti.Pharmeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int images[]={
            R.drawable.i,
            R.drawable.prescription,
            R.drawable.order,
            R.drawable.deliver

    };
    int headings[]={
            R.string.texttitle,
            R.string.texttitle2,
            R.string.texttitle3,
            R.string.texttitle4
    };
    int description[]={
      R.string.textdesc,
      R.string.textdesc2,
      R.string.textdesc3,
      R.string.textdesc4
    };
    public ViewPagerAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view ==(LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.introimage1);
        TextView slideHeading =(TextView) view.findViewById(R.id.texttitle);
        TextView slideDescription = (TextView) view.findViewById(R.id.textdesc);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(description[position]);

        container.addView(view);
        return view;
    }

    public void destroyItem(@NonNull ViewGroup container,int position, @NonNull Object object){
        container.removeView((LinearLayout)object);
    }
}
