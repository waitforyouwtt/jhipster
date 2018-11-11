import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <DropdownItem tag={Link} to="/entity/log-user-info">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Log User Info
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/user-info">
      <FontAwesomeIcon icon="asterisk" />&nbsp;User Info
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/bank-info">
      <FontAwesomeIcon icon="asterisk" />&nbsp;Bank Info
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
