package widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nsz.android.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 下拉刷新和上拉加载的ListView
 *
 * @author LeeShenzhou
 */
public class PullToRefreshListView extends ListView implements OnScrollListener {

    // 头布局
    private View mHeaderView;
    private ImageView mImageArrow;
    private ProgressBar mProgressBar;
    private TextView mTxtState;
    private TextView mTxtUpdateTime;

    // 脚布局
    private View mFooterView;

    // 头布局的高度
    private int headerHeight;

    // 脚布局的高度
    private int footerHeight;

    private int mDownY;

    // 下拉刷新
    private final int DOWN_REFRESH = 0;
    // 松开刷新
    private final int RELEASE_REFRESH = 1;
    // 正在刷新中
    private final int REFRESHING = 2;

    private int currentState = DOWN_REFRESH;

    private boolean isLoadMore;

    // 是否需要下拉刷新
    private boolean isNeedPullToRefresh;
    // 是否需要上拉加载更多
    private boolean isNeedLoadMore;

    /**
     * 是否需要下拉刷新
     */
    public void setNeedPullToRefresh(boolean need) {
        this.isNeedPullToRefresh = need;
    }

    /**
     * 是否需要上拉加载更多
     */
    public void setNeedLoadMore(boolean need) {
        this.isNeedLoadMore = need;
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        isLoadMore = false;
        currentState = DOWN_REFRESH;
        mHeaderView.setPadding(0, -headerHeight, 0, 0);
        mFooterView.setPadding(0, -footerHeight, 0, 0);
    }

    private OnRefreshListener listener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public PullToRefreshListView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        isNeedLoadMore = true;
        isNeedPullToRefresh = true;

        isLoadMore = false;

        // 初始化头布局
        mHeaderView = View.inflate(getContext(), R.layout.widget_layout_pull_to_refresh_header, null);
        mImageArrow = (ImageView) mHeaderView.findViewById(R.id.header_image_arrow);
        mProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.header_pb);
        mTxtState = (TextView) mHeaderView.findViewById(R.id.header_state);
        mTxtUpdateTime = (TextView) mHeaderView.findViewById(R.id.header_update_time);

        mHeaderView.measure(0, 0); // 系统会帮我们测量出HeaderView的高度
        headerHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -headerHeight, 0, 0);
        addHeaderView(mHeaderView);

        // 初始化脚布局
        mFooterView = View.inflate(getContext(), R.layout.widget_layout_pull_to_refresh_footer, null);
        mFooterView.measure(0, 0);
        footerHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, -footerHeight, 0, 0);
        addFooterView(mFooterView);

        // 设置最后刷新时间
        setLastUpdateTime();

        setOnScrollListener(this);
    }

    private void setLastUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = sdf.format(System.currentTimeMillis());
        if (mTxtUpdateTime != null) {
            mTxtUpdateTime.setText("最新更新: " + time);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isNeedPullToRefresh) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                final int offset = (moveY - mDownY) / 2;
                int paddingTop = -headerHeight + offset;

                if (getFirstVisiblePosition() == 0 && paddingTop > -headerHeight) {
                    if (currentState != REFRESHING) {
                        if (offset > headerHeight && currentState == DOWN_REFRESH) {
                            // 完全显示了
                            currentState = RELEASE_REFRESH;
                            changeHeaderView();
                        } else if (offset < headerHeight && currentState == RELEASE_REFRESH) {
                            // 没有显示完全
                            currentState = DOWN_REFRESH;
                            changeHeaderView();
                        }
                    }

                    if (currentState == REFRESHING) {
                        mHeaderView.setPadding(0, offset, 0, 0);
                    } else {
                        mHeaderView.setPadding(0, paddingTop, 0, 0);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (currentState == REFRESHING) {
                    mHeaderView.setPadding(0, 0, 0, 0);
                    setSelection(0);
                    return super.onTouchEvent(ev);
                }

                // 判断当前的状态是松开刷新还是下拉刷新
                if (currentState == RELEASE_REFRESH) {
                    mHeaderView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    changeHeaderView();
                    setSelection(0);
                    if (listener != null) {
                        listener.onPullToRefresh();
                    }
                } else if (currentState == DOWN_REFRESH) {
                    mHeaderView.setPadding(0, -headerHeight, 0, 0);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void changeHeaderView() {
        switch (currentState) {
            case DOWN_REFRESH:
                // 下拉刷新
                mTxtState.setText("下拉刷新");
                mProgressBar.setVisibility(View.GONE);
                mImageArrow.setVisibility(View.VISIBLE);
                mImageArrow.setImageResource(R.drawable.ic_arrow_down);
                break;

            case RELEASE_REFRESH:
                // 松开刷新
                mTxtState.setText("释放立即刷新");
                mProgressBar.setVisibility(View.GONE);
                mImageArrow.setVisibility(View.VISIBLE);
                mImageArrow.setImageResource(R.drawable.ic_arrow_pull);
                break;

            case REFRESHING:
                // 正在刷新
                mImageArrow.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                mTxtState.setText("正在刷新...");
                break;

            default:
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isNeedLoadMore || isLoadMore) {
            return;
        }

        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            // 判断当前是否已经到了底部
            if (getLastVisiblePosition() == (getCount() - 1)) {
                isLoadMore = true;
                mFooterView.setPadding(0, 0, 0, 0);
                setSelection(getCount());
                if (listener != null) {
                    listener.onLoadMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem != 0) {
            // 重置刷新状态
            if (currentState != REFRESHING) {
                currentState = DOWN_REFRESH;
            }
        }
    }

    public interface OnRefreshListener {

        /**
         * 下拉刷新
         */
        void onPullToRefresh();

        /**
         * 上拉加载更多
         */
        void onLoadMore();
    }

}
