package com.example.a32_fragments


/**Created by Raviteja Emandi on 16,June,2025 **/
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class MessageFragment : Fragment() {

    private var listener: OnMessageSendListener? = null

    interface OnMessageSendListener {
        fun onMessageSend(message: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MessageFragment", "onAttach")
        if (context is OnMessageSendListener) {
            listener = context
        } else {
            throw RuntimeException("Activity must implement OnMessageSendListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MessageFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MessageFragment", "onCreateView")
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val editText = view.findViewById<EditText>(R.id.edit_message)
        val button = view.findViewById<Button>(R.id.btn_send)

        button.setOnClickListener {
            val text = editText.text.toString()
            listener?.onMessageSend(text)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MessageFragment", "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MessageFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MessageFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MessageFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MessageFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MessageFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MessageFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("MessageFragment", "onDetach")
    }
}