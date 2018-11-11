import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './user-info.reducer';
import { IUserInfo } from 'app/shared/model/user-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IUserInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IUserInfoState = IPaginationBaseState;

export class UserInfo extends React.Component<IUserInfoProps, IUserInfoState> {
  state: IUserInfoState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { userInfoList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="user-info-heading">
          User Infos
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new User Info
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('userId')}>
                  User Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('userName')}>
                  User Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nickName')}>
                  Nick Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('sex')}>
                  Sex <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ascription')}>
                  Ascription <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('address')}>
                  Address <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('email')}>
                  Email <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('phoneNumber')}>
                  Phone Number <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('birthday')}>
                  Birthday <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('createTime')}>
                  Create Time <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('updateTime')}>
                  Update Time <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userInfoList.map((userInfo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${userInfo.id}`} color="link" size="sm">
                      {userInfo.id}
                    </Button>
                  </td>
                  <td>{userInfo.userId}</td>
                  <td>{userInfo.userName}</td>
                  <td>{userInfo.nickName}</td>
                  <td>{userInfo.sex}</td>
                  <td>{userInfo.ascription}</td>
                  <td>{userInfo.address}</td>
                  <td>{userInfo.email}</td>
                  <td>{userInfo.phoneNumber}</td>
                  <td>
                    <TextFormat type="date" value={userInfo.birthday} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={userInfo.createTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={userInfo.updateTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userInfo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userInfo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${userInfo.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ userInfo }: IRootState) => ({
  userInfoList: userInfo.entities,
  totalItems: userInfo.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserInfo);
