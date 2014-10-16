select 
    type_rel.NAZWA as typ_relacji,
    parent.NAZWA as rodzic,
    child.NAZWA as dziecko,
    parent.ID_JEDN_WN as rodzic_id,
    @pv:=child.ID_JEDN_WN as dziecko_id
from
    RELACJA_LEKS rel
        join
    JEDNOSTKA_WN parent ON (rel.RODZIC = parent.ID_JEDN_WN)
        join
    JEDNOSTKA_WN child ON (rel.DZIECKO = child.ID_JEDN_WN)
        join
    TYP_RELACJI type_rel ON (rel.RELACJA = type_rel.ID_TYP_RELACJI)
        join
    (SELECT @pv:=(SELECT ID_JEDN_WN FROM JEDNOSTKA_WN WHERE nazwa = "dawać")) tmp
where
    parent.ID_JEDN_WN = @pv
