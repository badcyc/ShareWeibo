package com.example.test.weibo.emoji;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.test.R;

/**
 * Created by cyc20 on 2017/12/15.
 */

public class EmojiFaceAdapter extends BaseAdapter {

    private Context context;
    public static int[] imageIds=new int[]{
            R.drawable.huanglianwx_org,R.drawable.ad_new0902_org,
            R.drawable.aliwanew_org,R.drawable.bba_org,R.drawable.pcmoren_baobao_org,R.drawable.pcmoren_tian_org,R.drawable.pcmoren_wu_org,
            R.drawable.moren_yunbei_org,R.drawable.moren_xiaoerbuyu_org,R.drawable.moren_feijie_org,R.drawable.moren_chongjing_org,
            R.drawable.moren_bbjdnew_org,R.drawable.huanglianwx_org,R.drawable.pcmoren_cool2017_org,R.drawable.tootha_org,R.drawable.laugh,
            R.drawable.tza_org,R.drawable.kl_org,R.drawable.wabi_org,R.drawable.cj_org,R.drawable.shamea_org,R.drawable.zy_org,R.drawable.bz_org,
            R.drawable.bs2_org,R.drawable.lovea_org,R.drawable.sada_org,R.drawable.heia_org,R.drawable.qq_org,R.drawable.sb_org,R.drawable.mb_org,
            R.drawable.landeln_org,R.drawable.yhh_org,R.drawable.zhh_org,R.drawable.x_org,R.drawable.cry,R.drawable.wq_org,R.drawable.t_org,
            R.drawable.haqianv2_org,R.drawable.bba_org,R.drawable.angrya_org,R.drawable.yw_org,R.drawable.cza_org,R.drawable.sk_org,
            R.drawable.sweata_org,R.drawable.kunv2_org,R.drawable.huangliansj_org,R.drawable.money_org,R.drawable.sw_org,R.drawable.huanglianse_org,
            R.drawable.hatea_org,R.drawable.gza_org,R.drawable.dizzya_org,R.drawable.bs_org,R.drawable.crazya_org,R.drawable.h_org,R.drawable.yx_org,
            R.drawable.numav2_org,R.drawable.hufen_org,R.drawable.dalian_org,R.drawable.shayan_org,R.drawable.gm_org,R.drawable.d_org,R.drawable.ok_org,
            R.drawable.ye_org,R.drawable.good_org,R.drawable.buyao_org,R.drawable.z2_org,R.drawable.come_org,R.drawable.sad_org,R.drawable.ws_org,
            R.drawable.o_org,R.drawable.ha_org,R.drawable.pcmoren_jiayou_org,R.drawable.hearta_org,R.drawable.unheart,R.drawable.ad_new0902_org,
            R.drawable.doge_org,R.drawable.mm_org,R.drawable.moren_hashiqi_org,R.drawable.xiaoku_org,R.drawable.pcmoren_tanshou_org,R.drawable.pcmoren_baobao_org,
            R.drawable.pcmoren_guile_org,R.drawable.moren_chiguaqunzhong_org,R.drawable.bhsj5_nainai_org,R.drawable.xman_kuaiyin_org,R.drawable.xman_baofengnv_org,
            R.drawable.mango_07_org,R.drawable.mango_12_org,R.drawable.mango_02_org,R.drawable.mango_03_org,R.drawable.mango_11_org,R.drawable.yangniandj_org
    };
    public static int[] imageIds2=new int[]{
            R.drawable.xiaohuangren_weixiao_org,R.drawable.xiaohuangren_baiyan_org,R.drawable.xiaohuangren_buxie_org,R.drawable.xiaohuangren_deyi_org,
            R.drawable.xiaohuangren_gaoxing_org,R.drawable.xiaohuangren_huaixiao_org,R.drawable.xiaohuangren_jiandaoshou_org,R.drawable.xiaohuangren_weiqu_org,
            R.drawable.xiaohuangren_jingya_org,R.drawable.xiaohuangren_wunai_org
    };
    public static int[] imageIds3=new int[]{
            R.drawable.yunying_zylmbianfuxia_org,R.drawable.yunying_zylmganggu_org,R.drawable.yunying_zylmhaiwang_org,R.drawable.yunying_zylmlogo_org,
            R.drawable.yunying_zylmshandianxia_org,R.drawable.yunying_zylmshenqi_org
    };
    public static int[] imageIds4=new int[]{
            R.drawable.dora_kaixin_org,R.drawable.dora_meiwei_org,R.drawable.dora_qinqin_org,R.drawable.dora_wunai_org,R.drawable.dora_xiao_org,
            R.drawable.dorachijing_org,R.drawable.dorahaipa_org,R.drawable.jqmbwtxing_org,R.drawable.jqmweixiao_org,R.drawable.jiqimaodaxiong_org,
            R.drawable.jiqimaojingxiang_org,R.drawable.jiqimaopanghu_org,R.drawable.jiqimaoxiaofu_org
    };
    public static int[][]imageIdsGroup={
            imageIds,imageIds2,imageIds3,imageIds4
    };
    private int page;
    public EmojiFaceAdapter(Context context,int page){
        this.context=context;
        this.page=page;
    }

    @Override
    public int getCount() {
        return imageIdsGroup[page-1].length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return imageIdsGroup[page-1][i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view==null){
            imageView=new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        else {
            imageView=(ImageView)view;
        }
        imageView.setImageResource(imageIdsGroup[page-1][i]);
        imageView.setTag("["+page+"]"+"["+i+"]");
        return imageView;
    }
}
