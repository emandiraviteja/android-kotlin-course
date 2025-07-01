# ✅  6.1  Permissions & Connectivity

---

## 👉 Internet Permission Setup

Android requires you to declare certain permissions in the `AndroidManifest.xml` before accessing features like Internet, Camera, Location, etc.

**Add this in `AndroidManifest.xml`:**

```
<uses-permission android:name="android.permission.INTERNET" />
```

---

## 👉 Checking Internet Connectivity

Before making a network request (e.g., to an API), you should always check whether the internet is available.

**Create NetworkUtils.kt:**
 ```
 import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
 ```

**Usage in Activity/Fragment**
 ```
 if (isNetworkAvailable(this)) {
    Toast.makeText(this, "Internet is available", Toast.LENGTH_SHORT).show()
} else {
    Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
}
 ```

**Best Practices**
| ✅ Tip                     | 📝 Description                        |
| ------------------------- | ------------------------------------- |
| Always check connectivity | Avoid crashes when making API calls   |
| Show feedback             | Use Toast or Snackbar to inform users |
| Don't block main thread   | Never use network logic on UI thread  |
| Use LiveData (optional)   | For real-time connectivity monitoring |

---

## 👉 Student task
**Task:**
Build an app with a button that checks internet status and shows a Toast or Snackbar.

---

## 👉 Mini Project to Check Internet

Project Highlights:

- Declares `INTERNET` and `ACCESS_NETWORK_STATE` in AndroidManifest.xml
- Includes `NetworkUtils.kt` to check internet availability
- Simple `MainActivity` with a button to test connectivity
- Clean layout with `Toast` messages