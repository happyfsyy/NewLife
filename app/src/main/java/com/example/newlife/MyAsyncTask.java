package com.example.newlife;

import android.app.ProgressDialog;
import android.os.AsyncTask;

/**
 * 假设要执行一个下载任务，以下是显示大概的过程。
 * 想要启动这个任务，只需要new MyAsyncTask().execute()，如果有params，就加入params。
 */
public class MyAsyncTask extends AsyncTask<Void,Integer,Boolean> {
    /**
     * 在执行new MyAsyncTask().execute()被调用后，立刻执行,还在后台任务开始执行之前调用。
     * 主要用于进行一些界面上的初始化操作。
     * 例如，显示一个进度条对话框等。
     */
    @Override
    protected void onPreExecute() {

//        ProgressDialog.show();
    }

    /**
     * 后台任务，方法内部已经开启了线程，所以这个方法中的所有代码都是在子线程中运行。
     * 我们应该在这里去处理所有的耗时任务。
     * 这个方法中是不可以进行UI操作的，如果要更新UI元素，只需要调用pulishProgress()方法，之后onPregressUpdate()就会被调用。
     * 任务一旦完成，可以通过return语句将任务的执行结果返回，如果第三个泛型参数是Void，可以不返回任何执行结果。
     */
    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    /**
     * 当后台任务中调用了pulishProgress()方法后，这个方法就会很快被调用。
     * 这个方法中可以利用参数中的数值对UI进行操作。
     * @param values 这个参数就是后台任务中传递过来的
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    /**
     * 当后台任务执行完毕并通过return语句进行返回后，这个方法很快就会被调用。
     * 可以利用返回的数据进行一些任务的收尾工作，例如提醒任务执行的结果，以及关闭掉进度条对话框等。
     * @param aBoolean 后台任务return的值
     */
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
