package com.group04.dictionary04.Categorization;

import android.arch.lifecycle.ReportFragment;
import android.support.v7.app.AppCompatDelegate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.group04.dictionary04.R;
import com.group04.dictionary04.model.default_Dictionary;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ReportFragment.class })


public class CategorizationUnitTest extends SearchViewActivity {

    private SearchViewActivity searchViewActivity;

    default_Dictionary dictionary;

    @Before
    public void setup() throws Exception {
        dictionary = new default_Dictionary();
    }

    //TODO test not currently working

    @Test
    public void testLaunch() {
        mockStatic(ReportFragment.class);
        SearchViewActivity activity = spy(new SearchViewActivity());

        doNothing().when(activity).setContentView(R.layout.searchview);
        doReturn(mock(AppCompatDelegate.class)).when(activity).getDelegate();
        // Call the method
        activity.onCreate(null);

        // Verify that it worked
        verify(activity, times(1)).setContentView(R.layout.searchview);
    }
}
