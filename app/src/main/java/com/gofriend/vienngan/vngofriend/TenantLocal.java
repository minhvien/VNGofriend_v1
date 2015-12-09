package com.gofriend.vienngan.vngofriend;

import com.gofriend.vienngan.vngofriend.InternalDataPipe.InternalData;
import com.gofriend.vienngan.vngofriend.model.User;

/**
 * Created by tmvien on 12/9/15.
 */
public class TenantLocal implements InternalData {

    public String tenantName;
    public User currentUser;

    public void clearData() {
        tenantName = null;
        currentUser = null;
    }
}
