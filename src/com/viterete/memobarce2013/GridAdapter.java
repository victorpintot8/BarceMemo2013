package com.viterete.memobarce2013;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter{
private Context context;
    private int count;
    private boolean flag;

public GridAdapter(Context c, int count1){
	this.context=c;
    this.count=count1;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
            ImageView image;

            if(convertView==null){
                image=new ImageView(context);
                int density= context.getResources().getDisplayMetrics().densityDpi;

                switch(density)
                {

                    case DisplayMetrics.DENSITY_MEDIUM:
                        if(count==20){
                            image.setLayoutParams(new GridView.LayoutParams(60,60));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,6,8,6);
                        }else{
                            image.setLayoutParams(new GridView.LayoutParams(75,75));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,8,8,8);
                        }

                        break;
                    case DisplayMetrics.DENSITY_HIGH:
                        if(count==20){
                            image.setLayoutParams(new GridView.LayoutParams(90,90));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,6,8,6);
                        }else{
                            image.setLayoutParams(new GridView.LayoutParams(110,110));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,8,8,8);
                        }

                        break;
                    case DisplayMetrics.DENSITY_XHIGH:
                        if(count==20){
                            image.setLayoutParams(new GridView.LayoutParams(145,145));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,6,8,6);
                        }else{
                            image.setLayoutParams(new GridView.LayoutParams(175,175));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(10,10,10,10);
                        }

                        break;
                }


            }
            else{
                image=(ImageView) convertView;
            }
            image.setImageResource(R.drawable.bsc14);
            return image;
	}

}
