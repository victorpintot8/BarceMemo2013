package com.viterete.memobarce2013;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Victor on 7/06/13.
 */

public class GridAdapterTemp extends BaseAdapter {
    private Context context;
    private int count;
    private int img0;
    private int img1;
    private int img2;
    private int img3;
    private int img4;
    private int img5;
    private int img6;
    private int img7;
    private int img8;
    private int img9;
    private int img10;
    private int img11;
    private int img12;
    private int img13;
    private int img14;
    private int img15;
    private int img16;
    private int img17;
    private int img18;
    private int img19;



    public GridAdapterTemp(Context c, int count1, int integer, int integer1, int integer2, int integer3, int integer4, int integer5, int integer6, int integer7, int integer8, int integer9, int integer10, int integer11) {
        this.context=c;
        this.count=count1;
        this.img0=integer;
        this.img1=integer1;
        this.img2=integer2;
        this.img3=integer3;
        this.img4=integer4;
        this.img5=integer5;
        this.img6=integer6;
        this.img7=integer7;
        this.img8=integer8;
        this.img9=integer9;
        this.img10=integer10;
        this.img11=integer11;
    }
    public GridAdapterTemp(Context c, int count1, int integer, int integer1, int integer2, int integer3, int integer4, int integer5, int integer6, int integer7, int integer8, int integer9, int integer10, int integer11, int integer12, int integer13, int integer14, int integer15, int integer16, int integer17, int integer18, int integer19) {
        this.context=c;
        this.count=count1;
        this.img0=integer;
        this.img1=integer1;
        this.img2=integer2;
        this.img3=integer3;
        this.img4=integer4;
        this.img5=integer5;
        this.img6=integer6;
        this.img7=integer7;
        this.img8=integer8;
        this.img9=integer9;
        this.img10=integer10;
        this.img11=integer11;
        this.img12=integer12;
        this.img13=integer13;
        this.img14=integer14;
        this.img15=integer15;
        this.img16=integer16;
        this.img17=integer17;
        this.img18=integer18;
        this.img19=integer19;
    }
    public GridAdapterTemp(Context c, int count1, int integer, int integer1, int integer2, int integer3, int integer4, int integer5, int integer6, int integer7, int integer8, int integer9, int integer10, int integer11, int integer12, int integer13, int integer14, int integer15) {
        this.context=c;
        this.count=count1;
        this.img0=integer;
        this.img1=integer1;
        this.img2=integer2;
        this.img3=integer3;
        this.img4=integer4;
        this.img5=integer5;
        this.img6=integer6;
        this.img7=integer7;
        this.img8=integer8;
        this.img9=integer9;
        this.img10=integer10;
        this.img11=integer11;
        this.img12=integer12;
        this.img13=integer13;
        this.img14=integer14;
        this.img15=integer15;
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
            int density1= (int)context.getResources().getDisplayMetrics().density;
            int width= context.getResources().getDisplayMetrics().widthPixels;
            if((width/density1)>=720){
                if(count==20){
                    image.setLayoutParams(new GridView.LayoutParams(185,185));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(12,20,12,20);
                }
                else if(count==16){
                    image.setLayoutParams(new GridView.LayoutParams(200,200));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(12,15,12,15);
                }
                else{
                    image.setLayoutParams(new GridView.LayoutParams(230,230));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(12,20,12,20);
                }
            }
            else if((width/density1)>=600){
                if(count==20){
                    image.setLayoutParams(new GridView.LayoutParams(135,135));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(8,6,8,6);
                }
                else if(count==16){
                    image.setLayoutParams(new GridView.LayoutParams(150,150));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(6,6,6,6);
                }
                else{
                    image.setLayoutParams(new GridView.LayoutParams(170,170));
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(6,6,6,6);
                }
            }
            else{
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
                            image.setLayoutParams(new GridView.LayoutParams(150,150));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,6,8,6);
                        }
                        else if(count==16){
                            image.setLayoutParams(new GridView.LayoutParams(175,175));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(10,10,10,10);
                        }
                        else{
                            image.setLayoutParams(new GridView.LayoutParams(195,195));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(10,10,10,10);
                        }
                        break;
                    case DisplayMetrics.DENSITY_XXHIGH:
                        if(count==20){
                            image.setLayoutParams(new GridView.LayoutParams(160,160));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(8,6,8,6);
                        }
                        else if(count==16){
                            image.setLayoutParams(new GridView.LayoutParams(190,190));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(10,10,10,10);
                        }
                        else{
                            image.setLayoutParams(new GridView.LayoutParams(205,205));
                            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            image.setPadding(10,10,10,10);
                        }
                        break;
                }
            }


        }
        else{
            image=(ImageView) convertView;
        }

        if(count==12){
        switch(position){
            case 0:image.setImageResource(img0);
            break;
            case 1:image.setImageResource(img1);
                break;
            case 2:image.setImageResource(img2);
                break;
            case 3:image.setImageResource(img3);
                break;
            case 4:image.setImageResource(img4);
                break;
            case 5:image.setImageResource(img5);
                break;
            case 6:image.setImageResource(img6);
                break;
            case 7:image.setImageResource(img7);
                break;
            case 8:image.setImageResource(img8);
                break;
            case 9:image.setImageResource(img9);
                break;
            case 10:image.setImageResource(img10);
                break;
            case 11:image.setImageResource(img11);
                break;
        }
        }
        else if(count==16){
            switch(position){
                case 0:image.setImageResource(img0);
                    break;
                case 1:image.setImageResource(img1);
                    break;
                case 2:image.setImageResource(img2);
                    break;
                case 3:image.setImageResource(img3);
                    break;
                case 4:image.setImageResource(img4);
                    break;
                case 5:image.setImageResource(img5);
                    break;
                case 6:image.setImageResource(img6);
                    break;
                case 7:image.setImageResource(img7);
                    break;
                case 8:image.setImageResource(img8);
                    break;
                case 9:image.setImageResource(img9);
                    break;
                case 10:image.setImageResource(img10);
                    break;
                case 11:image.setImageResource(img11);
                    break;
                case 12:image.setImageResource(img12);
                    break;
                case 13:image.setImageResource(img13);
                    break;
                case 14:image.setImageResource(img14);
                    break;
                case 15:image.setImageResource(img15);
                    break;
            }
        }
        else if(count==20){
            switch(position){
                case 0:image.setImageResource(img0);
                    break;
                case 1:image.setImageResource(img1);
                    break;
                case 2:image.setImageResource(img2);
                    break;
                case 3:image.setImageResource(img3);
                    break;
                case 4:image.setImageResource(img4);
                    break;
                case 5:image.setImageResource(img5);
                    break;
                case 6:image.setImageResource(img6);
                    break;
                case 7:image.setImageResource(img7);
                    break;
                case 8:image.setImageResource(img8);
                    break;
                case 9:image.setImageResource(img9);
                    break;
                case 10:image.setImageResource(img10);
                    break;
                case 11:image.setImageResource(img11);
                    break;
                case 12:image.setImageResource(img12);
                    break;
                case 13:image.setImageResource(img13);
                    break;
                case 14:image.setImageResource(img14);
                    break;
                case 15:image.setImageResource(img15);
                    break;
                case 16:image.setImageResource(img16);
                    break;
                case 17:image.setImageResource(img17);
                    break;
                case 18:image.setImageResource(img18);
                    break;
                case 19:image.setImageResource(img19);
                    break;
            }
        }
        return image;
    }

}
