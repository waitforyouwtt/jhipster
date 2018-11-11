import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bank-info.reducer';
import { IBankInfo } from 'app/shared/model/bank-info.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BankInfoDetail extends React.Component<IBankInfoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bankInfoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            BankInfo [<b>{bankInfoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bankId">Bank Id</span>
              <UncontrolledTooltip target="bankId">记录Id</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.bankId}</dd>
            <dt>
              <span id="userId">User Id</span>
              <UncontrolledTooltip target="userId">用户Id</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.userId}</dd>
            <dt>
              <span id="account">Account</span>
              <UncontrolledTooltip target="account">开户账号</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.account}</dd>
            <dt>
              <span id="accountName">Account Name</span>
              <UncontrolledTooltip target="accountName">开户名</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.accountName}</dd>
            <dt>
              <span id="bankName">Bank Name</span>
              <UncontrolledTooltip target="bankName">开户银行</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.bankName}</dd>
            <dt>
              <span id="bankBranchName">Bank Branch Name</span>
              <UncontrolledTooltip target="bankBranchName">银行支行</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.bankBranchName}</dd>
            <dt>
              <span id="amount">Amount</span>
              <UncontrolledTooltip target="amount">存款金额</UncontrolledTooltip>
            </dt>
            <dd>{bankInfoEntity.amount}</dd>
            <dt>
              <span id="createTime">Create Time</span>
              <UncontrolledTooltip target="createTime">创建时间</UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={bankInfoEntity.createTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="updateTime">Update Time</span>
              <UncontrolledTooltip target="updateTime">修改时间</UncontrolledTooltip>
            </dt>
            <dd>
              <TextFormat value={bankInfoEntity.updateTime} type="date" format={APP_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/bank-info" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/bank-info/${bankInfoEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ bankInfo }: IRootState) => ({
  bankInfoEntity: bankInfo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankInfoDetail);
