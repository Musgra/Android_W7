package vn.edu.hust.danhba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = arrayListOf<ItemModel>()
        val namesSet = mutableSetOf<String>()
        val names = mutableListOf<String>("John", "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hank", "Ivy", "Jack", "Karen", "Leo", "Mia", "Nathan", "Olivia", "Peter", "Quinn", "Rachel", "Sam", "Tom", "Ursula", "Victor", "Wendy")

        for (i in 1..24) {
            var randomName: String
            do {
                randomName = names.random()
            } while (randomName in namesSet)

            namesSet.add(randomName)

            val randomPhoneNumber = generateRandomPhoneNumber()

            itemList.add(
                ItemModel(
                    i,
                    randomName,
                    randomPhoneNumber,
                    "$randomName@gmail.com"
                )
            )
        }

        val adapter = MyCustomAdapter(itemList, this)
        val recyclerView: RecyclerView = findViewById(R.id.RVLayout)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        registerForContextMenu(recyclerView)
    }

    private fun generateRandomPhoneNumber(): String {
        val random = java.util.Random()
        val sb = StringBuilder("+84")  // Assuming you want to generate Vietnamese phone numbers
        for (i in 0 until 9) {
            sb.append(random.nextInt(10))
        }
        return sb.toString()
    }
}
