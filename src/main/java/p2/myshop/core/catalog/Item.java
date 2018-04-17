package p2.myshop.core.catalog;

import p1.myshop.entities.HasWeight;

public interface Item extends HasWeight {
    ItemId itemId();
}
