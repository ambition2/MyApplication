package lanbo.lanbo20171120.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import lanbo.lanbo20171120.R;
import lanbo.lanbo20171120.adapter.MyAdapter;
import lanbo.lanbo20171120.bean.Bean;
import lanbo.lanbo20171120.presenter.MyDdPresenter;
import lanbo.lanbo20171120.view.IShowDdView;

public class MainActivity extends AppCompatActivity implements IShowDdView, View.OnClickListener {
    MyDdPresenter presenter = new MyDdPresenter(this, this);
    private TextView mDzf;
    private TextView mYzf;
    private TextView mYqx;
    private XRecyclerView mRv;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findviewbyid
        initView();
        //先现实待支付状态下的订单
        presenter.ddtopostgetdata("0",i+"");
        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        mRv.setLayoutManager(manager);
    }

    private void initView() {
        mDzf = (TextView) findViewById(R.id.dzf);
        mDzf.setOnClickListener(this);
        mYzf = (TextView) findViewById(R.id.yzf);
        mYzf.setOnClickListener(this);
        mYqx = (TextView) findViewById(R.id.yqx);
        mYqx.setOnClickListener(this);
        mRv = (XRecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void ShowView(Bean bean) {
        final MyAdapter myAdapter=new MyAdapter(MainActivity.this,bean);
        mRv.setAdapter(myAdapter);

        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                i=0;
                presenter.ddtopostgetdata("0","0");
                myAdapter.notifyDataSetChanged();
                mRv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                i++;
                presenter.ddtopostgetdata("0",i+"");
                myAdapter.notifyDataSetChanged();
                mRv.loadMoreComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dzf:
            //当点击待支付时显示待支付下的订单
                presenter.ddtopostgetdata("0",i+"");
                break;
            case R.id.yzf:
                //当点击已支付时显示已支付下的订单
                presenter.ddtopostgetdata("1",i+"");
                break;
            case R.id.yqx:
                //当点击已取消时显示已取消下的订单
                presenter.ddtopostgetdata("2",i+"");
                break;
            default:
                break;
        }
    }
}
