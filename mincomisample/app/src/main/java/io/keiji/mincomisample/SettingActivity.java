package io.keiji.mincomisample;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, GeneralSettingFragment.newInstance(),
                        GeneralSettingFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().popBackStackImmediate()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return getFragmentManager().popBackStackImmediate();
        }
        return super.onOptionsItemSelected(item);
    }

    public static class GeneralSettingFragment extends PreferenceFragment
            implements Preference.OnPreferenceClickListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_general);

            findPreference("various").setOnPreferenceClickListener(this);
        }

        public static GeneralSettingFragment newInstance() {
            return new GeneralSettingFragment();
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {

            switch (preference.getKey()) {
                case "various":
                    getFragmentManager()
                            .beginTransaction()
                            .addToBackStack(OtherSettingFragment.class.getSimpleName())
                            .replace(android.R.id.content, OtherSettingFragment.newInstance(),
                                    OtherSettingFragment.class.getSimpleName())
                            .commit();
                    break;
            }

            return false;
        }
    }

    public static class OtherSettingFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.pref_other);

            findPreference("version").setSummary(BuildConfig.VERSION_NAME);
        }

        public static OtherSettingFragment newInstance() {
            return new OtherSettingFragment();
        }
    }
}
