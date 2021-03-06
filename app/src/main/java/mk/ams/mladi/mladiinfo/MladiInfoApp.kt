package mk.ams.mladi.mladiinfo

import android.app.Application
import android.content.Context
import mk.ams.mladi.mladiinfo.notifications.NotificationJobService

class MladiInfoApp : Application() {
  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(LocaleHelper.onAttach(base))
  }

  override fun onCreate() {
    super.onCreate()
    NotificationJobService.managedBasedOnPreferences(this)
  }
}