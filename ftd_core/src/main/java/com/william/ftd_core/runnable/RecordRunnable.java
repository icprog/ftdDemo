package com.william.ftd_core.runnable;

import android.content.ServiceConnection;

import com.william.ftd_core.ServerConnection;
import com.william.ftd_core.constant.ServiceApi;
import com.william.ftd_core.entity.AnalyzeResultBean;
import com.william.ftd_core.entity.ReportBean;
import com.william.ftd_core.entity.TendencyResult;
import com.william.ftd_core.entity.User;
import com.william.ftd_core.exception.FtdException;

import okhttp3.Response;

public class RecordRunnable implements Runnable {


    private Listener listener;

    public RecordRunnable(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.setCurrentThread(Thread.currentThread());
        ServerConnection conn = ServerConnection.getInstance();
        try {
            Response recordResp = conn.getRecoedBySeqNO(listener.getUser(), listener.getSeqNO());
            ReportBean recordResult = ServerConnection.checkResponse(recordResp, ReportBean.class);
//            this.bean = bean;
//            addDisposable(
//                    FtdClient.getInstance().getAnalyzer(bean.getUr(), this)
//            );
//            addDisposable(
//                    FtdClient.getInstance().getTendency(this)
//            );

//        if (reportType == REPORT_CARD){
//            bean.setScore(1.1d);
//        }
//            listener.onSuccess(recordResult);

            Response analyzeResp = conn.getAnalyzer(recordResult.getUr());
            AnalyzeResultBean analyzeResult = ServerConnection.checkResponse(analyzeResp, AnalyzeResultBean.class);
            recordResult.setAnalyzeResultBean(analyzeResult);

            Response tendencyResp = conn.getTendency();
            TendencyResult tendencyResult = ServerConnection.checkResponse(tendencyResp, TendencyResult.class);
            recordResult.setTendencyResult(tendencyResult);
            listener.onSuccess(recordResult);
        } catch (FtdException e) {
            listener.onFail(e);
        }
        listener.onComplete();
    }

    public interface Listener extends BaseRunnableMethods<ReportBean> {
        User getUser();

        long getSeqNO();
    }
}
