package bjfu.cs.zhouenjie.app;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import bjfu.cs.zhouenjie.Bean.Coffee;
import bjfu.cs.zhouenjie.Bean.Count;
import bjfu.cs.zhouenjie.Bean.User;
import bjfu.cs.zhouenjie.R;
import bjfu.cs.zhouenjie.utils.CacheUtils;
import bjfu.cs.zhouenjie.utils.SPUtils;

public class MyApplication extends Application {

    public static Context getmContext() {
        return mContext;
    }

    private static Context mContext;
    private static List<Coffee> mCoffeeList;
    private static List<Coffee> mFoodList;
    private static List<Coffee> mGoodList;
    private static List<Coffee> mAllList;
    private static List<Coffee> mCountList;
    private static List<Count> mCounts = new ArrayList<>();
    private static User user = new User();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CacheUtils.putString(getmContext(),"cart_json","");
    }

    // 判断是否已登录
    private static boolean isLogin = false;

    public static boolean isLogin() {
        return isLogin;
    }

    public static void setLogin(boolean login) {
        isLogin = login;
    }

    // 缓存所有账单
    public static void savaCounts(){
        SPUtils.putList(user.getUserName()+"_counts", mCounts);
    }

    // 取出账单信息
    public static void loadCounts(){
        mCounts = SPUtils.getList(user.getUserName()+"_counts", Count.class);
    }

    public static List<Coffee> getmCoffeeList() {
        return mCoffeeList;
    }

    public static void setmCoffeeList(List<Coffee> mCoffeeList) {
        mCoffeeList = mCoffeeList;
    }

    public static List<Coffee> getmFoodList() {
        return mFoodList;
    }

    public static void setmFoodList(List<Coffee> mFoodList) {
        mFoodList = mFoodList;
    }

    public static List<Coffee> getmGoodList() {
        return mGoodList;
    }

    public static void setmGoodList(List<Coffee> mGoodList) {
        mGoodList = mGoodList;
    }

    public static List<Coffee> getmAllList(){return mAllList;}

    static {
        mCoffeeList = new ArrayList<>();
        mCoffeeList.add(new Coffee("混凝土泵车", R.drawable.aa,300000,1,"14个米段、二桥到五桥4个桥式的4.0产品，这些4.0产品经济节能、易维护、适应复杂料况、智能等优势。"));
        mCoffeeList.add(new Coffee("车载式混凝土泵车",R.drawable.ab,450000,2,"高性能、高可靠、高回报的全新4.0混凝土车载泵。全系列设备采用自适应泵送控制技术，堵管率降低50%。"));
        mCoffeeList.add(new Coffee("混凝土布料机",R.drawable.ac,300000,3,"45米塔式布料机、29米/33米管柱式布料机、19米/22米移动式布料机等全新的中联重科4.0混凝土布料机陆续下线，这些产品具备高可靠性、形式多样等技术特点，提升了用户体验、为客户创造了更大价值。"));
        mCoffeeList.add(new Coffee("混凝土搅拌运输车",R.drawable.ad,250000,4,"从“技术、质量、成本、服务”四个极致方面提升产品核心竞争力，引领行业发展潮流，具有功能更完善、性能更优越、设计更先进，外形更美观、安全性可靠性更高，操纵更便利、驾乘更舒适等技术亮点。"));
        mCoffeeList.add(new Coffee("液压油",R.drawable.ae,2904,5,"经严格的液压泵和使用试验表明，油品不仅具有优异的抗磨性能，还具有良好的抗氧、抗乳化、抗泡、抗锈等性能。"));
        mCoffeeList.add(new Coffee("平头塔式起重机",R.drawable.af,150000,5,"针对目前装配式建筑市场，陆续开发出装配式建筑市场专用4.0系列平头产品，如T6513、T7020、T7530、T600、T1200等。这些产品传动高效、就位精度高、大附着悬高，也更加智能。"));
        mCoffeeList.add(new Coffee("锤头塔式起重机",R.drawable.ag,180000,5,"已打造了大、中、小型锤头塔式起重机全系列精品。"));
        mCoffeeList.add(new Coffee("汽车起重机",R.drawable.ah,200000,5,"4.0汽车起重机ZTC系列、ZTF系列陆续上市，产品更能吊、更能跑、更节能。"));
        mCoffeeList.add(new Coffee("履带式起重机",R.drawable.ai,230000,6,"围绕运营经济高效、作业安全可靠、管理智能便捷、使用绿色环保四大特点进行产品智能升级。"));
        mCoffeeList.add(new Coffee("标准节",R.drawable.aj,8120,6,"选用优质管材，材质为锰钢Q345B,造就产品超强刚性与耐用品质。各弦杆连接全部采用气体保护焊接，出厂全部经过严格精密检测，确保结构安全稳定。标准节全部经过整体抛丸加工工艺处理，成就优美外观。"));
        mCoffeeList.add(new Coffee("剪叉式高空作业平台",R.drawable.ak,400000,6,"27款车型，涵盖液压马达驱动和直流电机驱动两大类别，平台高度覆盖4-18米，全系列产品均可配置锂离子动力电池。"));
        mCoffeeList.add(new Coffee("直臂式高空作业平台",R.drawable.al,500000,6,"具有安全可靠、智能高效的特点，臂架伸缩平稳，越野爬坡行驶性能优越，具备行业领先的平台双负载能力（300kg/454kg）。\\n适用于大型场馆建设、市政桥梁、重工业厂房设施建设维护等施工环境。"));
        mCoffeeList.add(new Coffee("锂基脂",R.drawable.am,188,6,"不同于传统润滑脂，专用锂基脂在适温、耐磨、防水和泵送性能等指标中均得到大幅提升，满足设备施工需求，不惧恶劣工况挑战。"));
        mCoffeeList.add(new Coffee("全地面起重机",R.drawable.an,118,6,"在全地面起重机领域已形成200t-2000t的产品型谱，产品性能达到国际领先水平。"));
        mCoffeeList.add(new Coffee("美孚超级黑霸王",R.drawable.ao,419,6,"高级化学配方，优良的热稳定性，增强清洁分散性能，组件相容性。"));
        mCoffeeList.add(new Coffee("支腿",R.drawable.ap,4960,6,"适配机型：TC5013B-6、TC5510-6、TC5610-6、TCT5513-6、TCT5513-8。"));

        mFoodList = new ArrayList<>();
        mFoodList.add(new Coffee("电子油门踏板",R.drawable.aq,488,7,"适配机型：QY25V542.4;QY20V542;QY20D441;QY25V532.2;QY30V542;QY40V532.2。"));
        mFoodList.add(new Coffee("动臂塔式起重机",R.drawable.ar,410,8,"性能优、适用广、易维保、更智能的动臂塔式起重机产品，如L125-8/10、L500A-32/50等陆续面世。"));
        mFoodList.add(new Coffee("滑轮",R.drawable.as,197,9,"滑轮系列选用特种尼龙材质浇筑，不锈防潮。添加二硫化钼(MoS2)超微粉末，大幅提升物理性能，抵抗腐蚀，顺滑耐磨。"));

        mGoodList = new ArrayList<>();

        mAllList = new ArrayList<>();
        mAllList.addAll(mCoffeeList);
        mAllList.addAll(mFoodList);
        mAllList.addAll(mGoodList);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MyApplication.user = user;
    }

    public static List<Coffee> getmCountList() {
        return mCountList;
    }

    public static void setmCountList(List<Coffee> mCountList) {
        MyApplication.mCountList = mCountList;
    }

    public static List<Count> getmCounts() {
        return mCounts;
    }

    public static void setmCounts(List<Count> mCounts) {
        MyApplication.mCounts = mCounts;
    }


}
