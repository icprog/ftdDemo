package com.william.ftd_core.entity;

public class Conclusion {

    private FtdResponse<UploadResult> faceResult;
    private FtdResponse<UploadResult> tongueTopResult;
    private FtdResponse<UploadResult> tongueBottomResult;

    public FtdResponse<UploadResult> getFaceResult() {
        return faceResult;
    }

    public void setFaceResult(FtdResponse<UploadResult> faceResult) {
        this.faceResult = faceResult;
    }

    public FtdResponse<UploadResult> getTongueTopResult() {
        return tongueTopResult;
    }

    public void setTongueTopResult(FtdResponse<UploadResult> tongueTopResult) {
        this.tongueTopResult = tongueTopResult;
    }

    public FtdResponse<UploadResult> getTongueBottomResult() {
        return tongueBottomResult;
    }

    public void setTongueBottomResult(FtdResponse<UploadResult> tongueBottomResult) {
        this.tongueBottomResult = tongueBottomResult;
    }
}
