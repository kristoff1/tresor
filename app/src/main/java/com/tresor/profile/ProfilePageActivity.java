package com.tresor.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tresor.R;
import com.tresor.common.TresorActivity;

/**
 * Created by kris on 6/11/17. Tokopedia
 */

public class ProfilePageActivity extends TresorActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
    }

}
