package com.mvp.learningapp.thread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mvp.learningapp.BaseActivity;
import com.mvp.learningapp.R;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadActivity extends BaseActivity {
    //线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方 式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
    private ThreadPoolExecutor executor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_activity);
        /*
        * corePoolSize： 线程池中核心线程的数量。
        *maximumPoolSize：线程池中最大线程数量。
        *keepAliveTime：非核心线程的超时时长，当系统中非核心线程闲置时间超过keepAliveTime之后，则会被回收。如果ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，则该参数也表示核心线程的超时时长。
        *unit：keepAliveTime这个参数的单位，有纳秒、微秒、毫秒、秒、分、时、天等。
        *workQueue：线程池中的任务队列，该队列主要用来存储已经被提交但是尚未执行的任务。存储在这里的任务是由ThreadPoolExecutor的execute方法提交来的。
        *
        * */
        executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20));
        Button btn_run = (Button) findViewById(R.id.btn_run);
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 20; i++) {
                    final int finalI = i;
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                Log.i("dongpeng","current index is : " + finalI);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    executor.execute(runnable);
                }
            }
        });
    }
}
