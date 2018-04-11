package com.gvm.retrofittutorial;

import java.util.List;

public interface ControllerCallback {

    void onSuccess(List<Change> changes);

    void onError(String errorMsg);
}
