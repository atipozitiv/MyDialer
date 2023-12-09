package com.example.mydialer

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import timber.log.Timber
import timber.log.Timber.Forest.plant
import com.google.gson.JsonArray

data class Contact(
    val name: String = "",
    val phone: String = "",
    val type: String = "",
)

class Adapter(private val context: Context,
              private val list: List<Contact>):
              RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        var textName: TextView? = null
        var textPhone: TextView? = null
        var textType: TextView? = null
        init {
            textName = itemView.findViewById(R.id.textName)
            textPhone = itemView.findViewById(R.id.textPhone)
            textType = itemView.findViewById(R.id.textType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textName?.text = list[position].name
        holder.textPhone?.text = list[position].phone
        holder.textType?.text = list[position].type
    }
}

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        plant(Timber.DebugTree())

        val myButton: Button = findViewById(R.id.btn_search)
        val myEdit: EditText = findViewById(R.id.et_search)
        val contacts = Gson().fromJson(jsonStr, Array<Contact>::class.java).toList()
        myButton.setOnClickListener {
            if ((myEdit.text.toString() == "-") || (myEdit.text.toString() == "")) {
                val recyclerView: RecyclerView = findViewById(R.id.rView)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = Adapter(this, contacts)
            } else {
                val newContacts = ArrayList<Contact>()
                for(i in contacts.indices) {
                    if (contacts[i].name.contains(myEdit.text.toString()) || contacts[i].phone.contains(myEdit.text.toString()) || contacts[i].type.contains(myEdit.text.toString())) {
                        newContacts.add(contacts[i])
                    }
                }
                val recyclerView: RecyclerView = findViewById(R.id.rView)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = Adapter(this, newContacts)
            }
        }
    }
}

val jsonStr = """ [
      {
        "name": "(Приёмная)",
        "phone": "+375 (2239) 7-17-80",
        "type": ""
      },
      {
        "name": "(Бухгалтерия)",
        "phone": "+375 (2239) 7-17-64",
        "type": ""
      },
      {
        "name": "(Бухгалтерия)",
        "phone": "+375 (2239) 7-18-08",
        "type": ""
      },
      {
        "name": "(Юридическое бюро)",
        "phone": "+375 (2239) 7-17-63",
        "type": ""
      },
      {
        "name": "(Отдел правовой и кадровой работы)",
        "phone": "+375 (2239) 7-17-93",
        "type": ""
      },
      {
        "name": "(Отдел материально-технического снабжения)",
        "phone": "+375 (2239) 7-18-12",
        "type": ""
      },
      {
        "name": "",
        "phone": "+375 44 712 36 26",
        "type": "Сектор сбыта бумаги"
      },
      {
        "name": "(Реализация на внутренний рынок)",
        "phone": "+375 (2239) 7-17-79",
        "type": "Сектор сбыта бумаги"
      },
      {
        "name": "(Реализация на внешний рынок)",
        "phone": "+375 (2239) 4-11-77",
        "type": "Сектор сбыта бумаги"
      },
      {
        "name": "(Реализация на внутренний рынок)",
        "phone": "+375 (2239) 7-18-25",
        "type": "Сектор сбыта бумаги"
      },
      {
        "name": "",
        "phone": "+375 44 580 09 70",
        "type": "Сектор сбыта продукции деревообработки"
      },
      {
        "name": "(Реализация продукции деревообработки)",
        "phone": "+375 (2239) 7-18-42",
        "type": "Сектор сбыта продукции деревообработки"
      },
      {
        "name": "(Реализация продукции деревообработки)",
        "phone": "+375 (2239) 3-64-71",
        "type": "Сектор сбыта продукции деревообработки"
      },
      {
        "name": "",
        "phone": "+375 29 352 25 20",
        "type": "Реализация домов, бань, беседок, клеёного бруса"
      },
      {
        "name": "",
        "phone": "+375 (2239) 7-18-43",
        "type": "Реализация домов, бань, беседок, клеёного бруса"
      },
      {
        "name": "(приемная)",
        "phone": "+375 (2239) 7-17-80",
        "type": "Факс"
      },
      {
        "name": "(отдел сбыта)",
        "phone": "+375 (2239) 7-17-79",
        "type": "Факс"
      },
      {
        "name": "(отдел материально-технического снабжения)",
        "phone": "+375 (2239) 7-17-82",
        "type": "Факс"
      },
      {
        "name": "(служба главного энергетика)",
        "phone": "+375 (2239) 7-18-06",
        "type": "Факс"
      }
    ]""".trimIndent()