document.addEventListener("DOMContentLoaded", function() {
    const pizzaSelect = document.getElementById("pizzaType");
    const toppingsSection = document.getElementById("toppingsSection");
    const baseToppingsInfo = document.getElementById("baseToppingsInfo");
    const livePriceEl = document.getElementById("livePrice");

    function updateToppingsSection() {
        if (!pizzaSelect) return;
        const selected = pizzaSelect.value;
        const opt = pizzaSelect.options[pizzaSelect.selectedIndex];
        if (!opt) return;

        const basePrice = opt.getAttribute("data-base-price");
        const defaultToppingsAttr = opt.getAttribute("data-default-toppings");
        const defaultDisplayNamesAttr = opt.getAttribute("data-default-display-names");
        const defaultList = defaultToppingsAttr ? defaultToppingsAttr.split(",") : [];
        const defaultNames = defaultDisplayNamesAttr ? defaultDisplayNamesAttr.split(",") : [];

        if (selected === "CUSTOM") {
            baseToppingsInfo.textContent =
                "Вы создаёте свою пиццу. Базовая цена: " + basePrice + " руб. Выберите топпинги ниже.";
        } else {
            if (defaultNames.length > 0 && defaultNames[0] !== "") {
                baseToppingsInfo.textContent =
                    "Базовые ингредиенты: " + defaultNames.join(", ") +
                    ". Базовая цена: " + basePrice + " руб. Вы можете добавить дополнительные топпинги ниже.";
            } else {
                baseToppingsInfo.textContent =
                    "Базовая цена: " + basePrice + " руб. Вы можете добавить топпинги ниже.";
            }
        }
        if (toppingsSection) {
            toppingsSection.style.display = "block";
            const checkboxes = toppingsSection.querySelectorAll("input[type=checkbox]");
            checkboxes.forEach(function(chk) {
                const wrapper = chk.closest(".checkbox-item");
                if (wrapper) {
                    wrapper.style.display = "block";
                }
            });
        }
    }

    function updateLivePrice() {
        if (!pizzaSelect || !livePriceEl) return;
        const opt = pizzaSelect.options[pizzaSelect.selectedIndex];
        if (!opt) {
            livePriceEl.textContent = "Итого: 0 руб.";
            return;
        }
        let total = 0;
        const basePrice = parseInt(opt.getAttribute("data-base-price") || "0", 10);
        total += basePrice;
        if (toppingsSection) {
            toppingsSection.querySelectorAll("input[type=checkbox]").forEach(function(chk) {
                if (chk.checked) {
                    const p = parseInt(chk.getAttribute("data-price") || "0", 10);
                    total += p;
                }
            });
        }
        livePriceEl.textContent = "Итого: " + total + " руб";
    }

    if (pizzaSelect) {
        pizzaSelect.addEventListener("change", function() {
            updateToppingsSection();
            updateLivePrice();
        });
        updateToppingsSection();
    }
    if (toppingsSection) {
        toppingsSection.querySelectorAll("input[type=checkbox]").forEach(function(chk) {
            chk.addEventListener("change", updateLivePrice);
        });
    }
    updateLivePrice();
});
