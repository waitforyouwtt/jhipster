import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, getPaginationItemsNumber, JhiPagination } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bank-info.reducer';
import { IBankInfo } from 'app/shared/model/bank-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IBankInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IBankInfoState = IPaginationBaseState;

export class BankInfo extends React.Component<IBankInfoProps, IBankInfoState> {
  state: IBankInfoState = {
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
    const { bankInfoList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="bank-info-heading">
          Bank Infos
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Bank Info
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  ID <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('bankId')}>
                  Bank Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('userId')}>
                  User Id <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('account')}>
                  Account <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('accountName')}>
                  Account Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('bankName')}>
                  Bank Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('bankBranchName')}>
                  Bank Branch Name <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('amount')}>
                  Amount <FontAwesomeIcon icon="sort" />
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
              {bankInfoList.map((bankInfo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${bankInfo.id}`} color="link" size="sm">
                      {bankInfo.id}
                    </Button>
                  </td>
                  <td>{bankInfo.bankId}</td>
                  <td>{bankInfo.userId}</td>
                  <td>{bankInfo.account}</td>
                  <td>{bankInfo.accountName}</td>
                  <td>{bankInfo.bankName}</td>
                  <td>{bankInfo.bankBranchName}</td>
                  <td>{bankInfo.amount}</td>
                  <td>
                    <TextFormat type="date" value={bankInfo.createTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={bankInfo.updateTime} format={APP_DATE_FORMAT} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankInfo.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ bankInfo }: IRootState) => ({
  bankInfoList: bankInfo.entities,
  totalItems: bankInfo.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankInfo);
