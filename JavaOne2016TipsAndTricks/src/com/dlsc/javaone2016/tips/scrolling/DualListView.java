package com.dlsc.javaone2016.tips.scrolling;

import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class DualListView extends HBox {

	private ListView<String> listViewA = new ListView<>();
	private ListView<String> listViewB = new ListView<>();

	public DualListView() {
		HBox.setHgrow(listViewA, Priority.ALWAYS);
		HBox.setHgrow(listViewB, Priority.ALWAYS);

		getChildren().add(listViewA);
		getChildren().add(listViewB);

		for (int i = 0; i < 100; i++) {
			listViewA.getItems().add("Left Item #" + i);
			listViewB.getItems().add("Right Item #" + i);
		}

		listViewA.skinProperty().addListener(it -> bindScrollBars());
		listViewB.skinProperty().addListener(it -> bindScrollBars());
	}

	private Optional<ScrollBar> findScrollBar(Parent parent) {
		return parent.lookupAll("VirtualScrollBar")
				.stream().map(node -> (ScrollBar) node)
				.filter(bar -> bar.getOrientation().equals(Orientation.VERTICAL))
				.findFirst();
	}

	private void bindScrollBars() {
		Optional<ScrollBar> scrollBarA = findScrollBar(listViewA);
		Optional<ScrollBar> scrollBarB = findScrollBar(listViewB);

		if (scrollBarA.isPresent() && scrollBarB.isPresent()) {
			ScrollBar barA = scrollBarA.get();
			ScrollBar barB = scrollBarB.get();

			Bindings.bindBidirectional(barA.valueProperty(), barB.valueProperty());
			Bindings.bindBidirectional(barA.visibleAmountProperty(), barB.visibleAmountProperty());
			Bindings.bindBidirectional(barA.blockIncrementProperty(), barB.blockIncrementProperty());
			Bindings.bindBidirectional(barA.unitIncrementProperty(), barB.unitIncrementProperty());
			Bindings.bindBidirectional(barA.minProperty(), barB.minProperty());
			Bindings.bindBidirectional(barA.maxProperty(), barB.maxProperty());
		}
	}
}
