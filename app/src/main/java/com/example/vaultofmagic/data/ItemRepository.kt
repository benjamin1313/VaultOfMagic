package com.example.vaultofmagic.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vaultofmagic.models.DndItem

class ItemRepository {

    private val _itemList = MutableLiveData<List<DndItem>>()

    init {
        // Initialize some hardcoded data (seems there are problems getting this to work.
        // So getFakeData will be used in the getter functions as well.)
        val itemList:List<DndItem> = getFakeItemData()
        _itemList.postValue(itemList)
    }

    private fun getFakeItemData(): List<DndItem> {
        val items: List<DndItem> = listOf(
            DndItem(1,
                "Bag of Holding",
                "Wondrous item",
                "Uncommon",
                "This bag has an interior space considerably larger than its outside dimensions, roughly 2 feet in diameter at the mouth and 4 feet deep. The bag can hold up to 500 pounds, not exceeding a volume of 64 cubic feet. The bag weighs 15 pounds, regardless of its contents. Retrieving an item from the bag requires an action.\n" +
                        "\n" +
                        "If the bag is overloaded, pierced, or torn, it ruptures and is destroyed, and its contents are scattered in the Astral Plane. If the bag is turned inside out, its contents spill forth, unharmed, but the bag must be put right before it can be used again.\n" +
                        "\n" +
                        "Breathing creatures inside the bag can survive up to a number of minutes equal to 10 divided by the number of creatures (minimum 1 minute), after which time they begin to suffocate.\n" +
                        "\n" +
                        "Placing a bag of holding inside an extradimensional space created by a handy haversack, portable hole, or similar item instantly destroys both items and opens a gate to the Astral Plane. The gate originates where the one item was placed inside the other. Any creature within 10 feet of the gate is sucked through it to a random location on the Astral Plane. The gate then closes. The gate is one-way only and can’t be reopened."
            ),
            DndItem(2,
                "Bag of Tricks",
                "Wondrous item",
                "Uncommon",
                "This ordinary bag, made from gray, rust, or tan cloth, appears empty. Reaching inside the bag, however, reveals the presence of a small, fuzzy object. The bag weighs 1/2 pound.\n" +
                        "\n" +
                        "You can use an action to pull the fuzzy object from the bag and throw it up to 20 feet. When the object lands, it transforms into a creature you determine by rolling a d8 and consulting the table that corresponds to the bag’s color.\n" +
                        "\n" +
                        "The creature is friendly to you and your companions, and it acts on your turn. You can use a bonus action to command how the creature moves and what action it takes on its next turn, or to give it general orders, such as to attack your enemies. In the absence of such orders, the creature acts in a fashion appropriate to its nature.\n" +
                        "\n" +
                        "Once three fuzzy objects have been pulled from the bag, the bag can’t be used again until the next dawn."
            ),DndItem(3,
                "Sun Blade",
                "Weapon (longsword)",
                "Rare",
                "This item appears to be a longsword hilt. While grasping the hilt, you can use a bonus action to cause a blade of pure radiance to spring into existence, or make the blade disappear. While the blade exists, this magic longsword has the finesse property. If you are proficient with shortswords or longswords, you are proficient with the sun blade.\n" +
                        "\n" +
                        "You gain a +2 bonus to attack and damage rolls made with this weapon, which deals radiant damage instead of slashing damage. When you hit an undead with it, that target takes an extra 1d8 radiant damage.\n" +
                        "\n" +
                        "The sword’s luminous blade emits bright light in a 15-foot radius and dim light for an additional 15 feet.\n" +
                        "\n" +
                        "The light is sunlight. While the blade persists, you can use an action to expand or reduce its radius of bright and dim light by 5 feet each, to a maximum of 30 feet each or a minimum of 10 feet each."
            ),
        )
        return items
    }

    suspend fun saveItemData(itemModel: DndItem) {
        // ToDo
    }

    suspend fun updateMyData(itemModel: DndItem) {
        // ToDo
    }

    suspend fun deleteItemData(itemModel: DndItem) {
        val currentList = _itemList.value ?: emptyList()
        val newList = currentList.filter { it.id != itemModel.id }
        _itemList.postValue(newList)
    }

    suspend fun getAllItems(): List<DndItem>? { // Need to look into how this is done properly.
        if (_itemList.value == null){
            _itemList.postValue(getFakeItemData())
            // return _itemList.value // looks like this for some reason always returns null so I am just gonna return getFakeItemData() for now.
            return getFakeItemData()
        }

        return _itemList.value
    }
}