package jc.com.xbannerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> images;
    List<String> titles;
    XBanner banner;
    private RadioGroup transforms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (XBanner) findViewById(R.id.banner);
        transforms = (RadioGroup) findViewById(R.id.transforms);
        images = new ArrayList<>();
        titles = new ArrayList<>();
        images.add("http://img01.sogoucdn.com/app/a/100520024/f4d580ab0d9f5d514c9471b23bba0561");
        titles.add("美女1美女1");
        images.add("http://img03.sogoucdn.com/app/a/100520024/611aff966911ebb8f61df057f4822dd9");
        titles.add("美女2");
        images.add("http://img01.sogoucdn.com/app/a/100520024/2ac6c898d7d768e3aabd72b0fbb62e5a");
        titles.add("美女3");
        images.add("http://img01.sogoucdn.com/app/a/100520024/bc84efff94797560fd239a883712cf71");
        titles.add("美女4");
        // 为XBanner绑定数据
        banner.setData(images, titles);
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(MainActivity.this).load(images.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效  Default默认的
        banner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        // RadioGroup中选中某个RadioButton时回调的方法
        transforms.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_default:
                        banner.setPageTransformer(Transformer.Default);
                        break;
                    case R.id.rb_alpha:
                        banner.setPageTransformer(Transformer.Alpha);
                        break;
                    case R.id.rb_rotate:
                        banner.setPageTransformer(Transformer.Rotate);
                        break;
                    case R.id.rb_cube:
                        banner.setPageTransformer(Transformer.Cube);
                        break;
                    case R.id.rb_flip:
                        banner.setPageTransformer(Transformer.Flip);
                        break;
                    case R.id.rb_accordion:
                        banner.setPageTransformer(Transformer.Accordion);
                        break;
                    case R.id.rb_zoomfade:
                        banner.setPageTransformer(Transformer.ZoomFade);
                        break;
                    case R.id.rb_zoomcenter:
                        banner.setPageTransformer(Transformer.ZoomCenter);
                        break;
                    case R.id.rb_zoomstack:
                        banner.setPageTransformer(Transformer.ZoomStack);
                        break;
                    case R.id.rb_stack:
                        banner.setPageTransformer(Transformer.Stack);
                        break;
                    case R.id.rb_depth:
                        banner.setPageTransformer(Transformer.Depth);
                        break;
                }
            }
        });
        // XBanner中某一项的点击事件
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //当Activity失去焦点时立即停止自动轮播；当Activity获得焦点时才开始自动轮播
    @Override
    protected void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
