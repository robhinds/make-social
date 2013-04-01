package com.tmm.maker.core.dao;

import java.util.List;

import com.tmm.maker.domain.Profile;

public interface ProfileDAO {

	List<Profile> loadAllProfiles();

	List<Profile> loadProfiles(Long[] ids);
}