# ✅ 4.1 RecyclerView Overview

RecyclerView is used to display large sets of data efficiently by recycling views.

Key Concepts

| Concept           | Description                               |
| ----------------- | ----------------------------------------- |
| **Adapter**       | Binds your data to the views              |
| **ViewHolder**    | Holds view references for reuse           |
| **LayoutManager** | Controls layout (e.g., vertical, grid)    |
| **ListAdapter**   | Advanced adapter with built-in `DiffUtil` |
| **DiffUtil**      | Calculates minimal updates to the list    |
| **Item Clicks**   | Handled in ViewHolder or Adapter          |

<br>

## 👉 Adapter & ViewHolder Patterns

**ViewHolder Pattern**

```
   class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   val textView: TextView = itemView.findViewById(R.id.textView)
   }
```

**Adapter Pattern**

```
class MyAdapter(private val items: List<String>) :
RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = items[position]
    }

    override fun getItemCount(): Int = items.size
}
```

## 👉 ListAdapter & DiffUtil for Efficient Updates

ListAdapter automatically calculates differences in your list using DiffUtil.


**DiffUtil.ItemCallback**

```
class MyDiffCallback : DiffUtil.ItemCallback<String>() {
override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}
```

**ListAdapter**

```
class MyListAdapter : ListAdapter<String, MyViewHolder>(MyDiffCallback()) {
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
val view = LayoutInflater.from(parent.context)
.inflate(R.layout.item_layout, parent, false)
return MyViewHolder(view)
}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = getItem(position)
    }
}
```

## 👉 Handling Item Clicks

You can handle clicks inside onBindViewHolder or through callback interfaces:

**Simple way inside Adapter:**

```
override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
val item = getItem(position)
holder.textView.text = item
holder.itemView.setOnClickListener {
Toast.makeText(holder.itemView.context, "Clicked: $item", Toast.LENGTH_SHORT).show()
}
}
```

## 👉 Mini RecyclerView App Structure

MainActivity

RecyclerView in activity_main.xml

item_layout.xml for row UI

ListAdapter + DiffUtil

Optional: Handle clicks and update list dynamically

<br>

**Project Overview**

    App displays a list of names.
    Tapping an item shows a Toast.
    A button adds a new name (live update via ListAdapter).

Step 1: Create Project & Dependencies

Step 2: activity_main.xml

Step 3: item_name.xml (RecyclerView Row)

Step 4: ViewHolder & Adapter (with ListAdapter + DiffUtil)

Step 5: MainActivity.kt

<br>


**Final Output**

List of names shown in RecyclerView

Tap a name → shows Toast

Tap Add Name → new name appears (efficient update via DiffUtil)

<br>

**Quick Summary**

| Element          | Role                                       |
| ---------------- | ------------------------------------------ |
| `ListAdapter`    | Handles diffing and efficient updates      |
| `DiffUtil`       | Tells adapter what's changed               |
| `onClick` lambda | Passed from activity for item click action |
