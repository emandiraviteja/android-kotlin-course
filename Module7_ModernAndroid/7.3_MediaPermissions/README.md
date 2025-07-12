# ✅  7.3 Permissions & Media Access
---

## 👉 Android Permissions?

Explain that Android protects sensitive data (camera, location, storage) using permissions. These are declared in the AndroidManifest.xml.
```
<!-- In AndroidManifest.xml -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
For Android 10+ use MediaStore API or Storage Access Framework instead of direct file paths.

### ⚡ Why Runtime Permissions?

Since Android 6.0 (API 23), dangerous permissions (like storage, location, camera) must be requested at runtime.

`Dangerous` = user privacy is at risk.

### ⚡ How to Request Runtime Permissions
**Example: **
Request `READ_EXTERNAL_STORAGE` Permission in Kotlin

```
private val STORAGE_PERMISSION_CODE = 1001

fun checkAndRequestPermission(context: Context, activity: Activity) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            STORAGE_PERMISSION_CODE
        )
    } else {
        Toast.makeText(context, "Permission already granted!", Toast.LENGTH_SHORT).show()
        openGallery()
    }
}

// Handle result
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<String>,
    grantResults: IntArray
) {
    if (requestCode == STORAGE_PERMISSION_CODE) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            openGallery()
        } else {
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show()
        }
    }
}
```

### ⚡ Accessing Media & Storage

**Example:**
Pick an Image from Gallery (using Intent)
```
private val GALLERY_REQUEST_CODE = 1002

fun openGallery() {
    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(intent, GALLERY_REQUEST_CODE)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
        val imageUri = data?.data
        imageView.setImageURI(imageUri) // Display in ImageView
    }
}
```

**Summary**

| Concept              | API / Tool                               | Notes                               |
| -------------------- | ---------------------------------------- | ----------------------------------- |
| Declare permissions  | `AndroidManifest.xml`                    | Required for all sensitive features |
| Runtime request      | `ActivityCompat.requestPermissions()`    | For dangerous permissions only      |
| Media Access         | `MediaStore`, `Intent.ACTION_PICK`       | Used for images, videos, audio etc. |
| Android 10+ (Scoped) | `Storage Access Framework`, `MediaStore` | File path access restricted         |

---

## 👉 Mini App: Gallery Picker with Runtime Permission

**Features:**

- Request READ_EXTERNAL_STORAGE at runtime (for API < 33)
- Pick image from gallery using intent
- Show the selected image in ImageView

AndroidManifest.xml - Permissions
res/layout/activity_main.xml
MainActivity.kt

**Test**
- Launch the app and click the “Pick Image” button.
- If permission not granted → it asks runtime.
- Once allowed → gallery opens.
- Select image → it appears in the app.