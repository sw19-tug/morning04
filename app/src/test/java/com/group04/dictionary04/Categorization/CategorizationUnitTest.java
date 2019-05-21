package com.group04.dictionary04.Categorization;

import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.group04.dictionary04.R;
import com.group04.dictionary04.SearchViewActivity;
import com.group04.dictionary04.model.default_Dictionary;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ ReportFragment.class })

public class CategorizationUnitTest extends SearchViewActivity {
    //TODO Unsure of the best way to do unit testing for this activity, go over on-site
//    private SearchViewActivity searchViewActivity;
//    private Context context = ApplicationProvider.getApplicationContext();
//
//    default_Dictionary dictionary;
//
//    @Before
//    public void setup() throws Exception {
//        super.setUp();
//        dictionary = new default_Dictionary();
//    }


    //TODO not feasible to test creation of activity
//    @Test
//    public void testLaunch() {
//        mockStatic(ReportFragment.class);
//        SearchViewActivity activity = spy(new SearchViewActivity());
//
//        doNothing().when(activity).setContentView(R.layout.searchview);
//        doReturn(mock(AppCompatDelegate.class)).when(activity).getDelegate();
//        // Call the method
//        activity.onCreate(null);
//
//        // Verify that it worked
//        verify(activity, times(1)).setContentView(R.layout.searchview);
//    }

}
